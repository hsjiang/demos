package views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import kotlin.math.abs

val TAG_EVENT_ACTION = "event_action"
val TAG_CAVANS_DRAW = "cavans_draw"

class PaintToolView : View {
    companion object {
        const val LINE: Int = 1
        const val ARROW: Int = 2
        const val ERASER: Int = 3
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(LINE, ARROW, ERASER)
    annotation class EditMode

    private val INVALID_POINTER = -1
    private val MIN_MOVE_DIS = 5

    private var mPointerId = INVALID_POINTER
    private var mInitialTouchX = 0f
    private var mInitialTouchY = 0f
    private var mLastTouchX = 0f
    private var mLastTouchY = 0f

    private var mDrawShape: DrawShape? = null
    private val mCanvas: Canvas = Canvas()
    private var mBitmap: Bitmap? = null
    private var mColor: Int = Color.RED
    private var mPaintWidth =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)

    private var mPathList = arrayListOf<DrawShape>()

    @EditMode
    private var mEditMode = LINE

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    init {
        addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (left != oldLeft || top != oldTop || right != oldRight || bottom != oldBottom) {
                mBitmap = Bitmap.createBitmap(right - left, bottom - top, Bitmap.Config.ARGB_8888)
                mCanvas.setBitmap(mBitmap)
                val bgPaint = Paint().apply {
                    style = Paint.Style.FILL
                    color = Color.GRAY
                }
                mCanvas.drawRect(Rect(left, top, right, bottom), bgPaint)
                invalidate()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            val actionIndex: Int = actionIndex
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    mPointerId = getPointerId(0)
                    mInitialTouchX = x
                    mInitialTouchY = y
                    mLastTouchX = x
                    mLastTouchY = y
                    onDown(x, y)
                    Log.d(TAG_EVENT_ACTION, "ACTION_DOWN mPointerId:${mPointerId}")
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    mPointerId = getPointerId(actionIndex)
                    mLastTouchX = getX(actionIndex)
                    mLastTouchY = getY(actionIndex)
                    onDown(mLastTouchX, mLastTouchY)
                    Log.d(TAG_EVENT_ACTION, "ACTION_POINTER_DOWN mPointerId:${mPointerId}")
                }
                MotionEvent.ACTION_MOVE -> {
                    val index: Int = findPointerIndex(mPointerId)
                    if (index < 0) return true

                    if (abs(x - mLastTouchX) >= MIN_MOVE_DIS || abs(y - mLastTouchY) >= MIN_MOVE_DIS) {
                        onMove(x, y)
                        mLastTouchX = x
                        mLastTouchY = y
                        Log.d(TAG_EVENT_ACTION, "ACTION_MOVE mPointerId:${mPointerId}")
                    }
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    val thisActionIndex = getActionIndex()
                    if (getPointerId(thisActionIndex) == mPointerId) {
                        val newIndex = if (thisActionIndex == 0) 1 else 0
                        mPointerId = getPointerId(newIndex)
                        mLastTouchX = getX(newIndex)
                        mLastTouchY = getY(newIndex)
                        onDown(mLastTouchX, mLastTouchY)
                    }
                    Log.d(TAG_EVENT_ACTION, "ACTION_POINTER_UP mPointerId:${mPointerId}")
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
                    onUp()
//                    val mPointerId = getPointerId(actionIndex)
//                    Log.d(TAG_EVENT_ACTION, "ACTION_UP mPointerId:${mPointerId}")
                }
                else -> return true
            }
        }
        return true
    }

    fun unDo() {
        if (mPathList.isEmpty()) return
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        mCanvas.setBitmap(mBitmap)

        mPathList.removeAt(mPathList.size - 1)
        mPathList.forEach {
            it.draw(mCanvas)
        }
        invalidate()
    }

    fun getBitmap(): Bitmap? {
        return mBitmap
    }

    fun setColor(@ColorInt color: Int) {
        mColor = color
        mDrawShape?.changeColor(mColor)
    }

    fun setEditMode(@EditMode mode: Int) {
        mEditMode = mode
    }

    override fun onDraw(canvas: Canvas?) {
        mBitmap?.also { canvas?.drawBitmap(it, 0f, 0f, null) }
    }

    private fun onMove(x: Float, y: Float) {
        mDrawShape?.move(mLastTouchX, mLastTouchY, x, y)
        mDrawShape?.draw(mCanvas)
        invalidate()
    }

    private fun onDown(x: Float, y: Float) {
        if (mDrawShape == null) {
            if (mEditMode == LINE) {
                val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = mColor
                    style = Paint.Style.STROKE
                    strokeWidth = mPaintWidth
                    strokeCap = Paint.Cap.ROUND
                    strokeJoin = Paint.Join.ROUND
                }
                mDrawShape = PathShape(paint)
            } else if (mEditMode == ERASER) {
                val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.TRANSPARENT
                    style = Paint.Style.STROKE
                    strokeWidth = 80f
                    strokeCap = Paint.Cap.ROUND
                    xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
                }
                mDrawShape = EraserShape(paint)
            }
        }
        mDrawShape?.down(x, y)
        mDrawShape?.draw(mCanvas)
        invalidate()
    }

    private fun onUp() {
        mDrawShape?.also { mPathList.add(it) }
        mDrawShape = null
    }

    private interface DrawShape {
        fun down(x: Float, y: Float)
        fun move(x1: Float, y1: Float, x2: Float, y2: Float)
        fun up(x: Float, y: Float) {}
        fun draw(canvas: Canvas)
        fun changeColor(@ColorInt color: Int) {}
    }

    private class PathShape(val paint: Paint) : DrawShape {
        private val path: Path = Path()

        override fun down(x: Float, y: Float) {
            path.moveTo(x, y)
        }

        override fun move(x1: Float, y1: Float, x2: Float, y2: Float) {
            path.quadTo(x1, y1, (x2 + x1) / 2, (y2 + y1) / 2)
        }

        override fun draw(canvas: Canvas) {
            canvas.drawPath(path, paint)
        }

        override fun changeColor(color: Int) {
            paint.color = color
        }
    }

    private class ArrowShape(val paint: Paint, val initialX: Float, val initialY: Float) :
        DrawShape {
        private val rectF = RectF(0f, 0f, 0f, 0f)
        private val trianglePath = Path()

        override fun down(x: Float, y: Float) {
            if (x != initialX || y != initialY) {

            }
        }

        override fun move(x1: Float, y1: Float, x2: Float, y2: Float) {

        }

        override fun draw(canvas: Canvas) {
            canvas.drawRect(rectF, paint)
            canvas.drawPath(trianglePath, paint)
        }

        override fun changeColor(color: Int) {
            paint.color = color
        }
    }

    private class EraserShape(val paint: Paint) : DrawShape {

        private val path = Path()

        override fun down(x: Float, y: Float) {
            path.addCircle(x, y, 20f, Path.Direction.CW)
            path.moveTo(x, y)
        }

        override fun move(x1: Float, y1: Float, x2: Float, y2: Float) {
            path.quadTo(x1, y1, (x2 + x1) / 2, (y2 + y1) / 2)
        }

        override fun draw(canvas: Canvas) {
            canvas.drawPath(path, paint)
        }
    }

//    private fun getArrow(sx: Float, sy: Float, ex: Float, ey: Float, width: Int): Path {
//        var size = 5
//        var count = 20
//        when (width) {
//            0 -> {
//                size = 5
//                count = 20
//            }
//            5 -> {
//                size = 8
//                count = 30
//            }
//            10 -> {
//                size = 11
//                count = 40
//            }
//        }
//        var x = ex - sx
//        var y = ey - sy
//        var d = (x * x + y * y).toDouble()
//        var r = sqrt(d)
//        val zx = (ex - (count * x / r)).toFloat()
//        val zy = (ey - (count * y / r)).toFloat()
//        val xz = zx - sx
//        val yz = zy - sy
//        val zd = (xz * xz + yz * yz).toDouble()
//        val zr = sqrt(zd)
//        val triangle = Path()
//        triangle.moveTo(sx, sy)
//        triangle.lineTo((zx + size * yz / zr).toFloat(), (zy - size * xz / zr).toFloat())
//        triangle.lineTo((zx + size * 2 * yz / zr).toFloat(), (zy - size * 2 * xz / zr).toFloat())
//        triangle.lineTo(ex, ey)
//        triangle.lineTo((zx - size * 2 * yz / zr).toFloat(), (zy + size * 2 * xz / zr).toFloat())
//        triangle.lineTo((zx - size * yz / zr).toFloat(), (zy + size * xz / zr).toFloat())
//        triangle.close()
//
//        return triangle
//    }
}