package views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import kotlin.math.*

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
    private var mColor: Int = Color.TRANSPARENT

    private var mShapeList = arrayListOf<DrawShape>()

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
                resetBitmap()
                setBg()
                invalidate()
            }
        }
    }

    private fun setBg() {
//        val bgPaint = Paint().apply {
//            style = Paint.Style.FILL
//            color = Color.GRAY
//        }
//        mCanvas.drawRect(Rect(left, top, right, bottom), bgPaint)
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
                    onDown(mLastTouchX, mLastTouchY)
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
                        val preX = mLastTouchX
                        val preY = mLastTouchY
                        mLastTouchX = x
                        mLastTouchY = y
                        onMove(preX, preY, x, y)
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
        if (mShapeList.isEmpty()) return
        mShapeList.removeAt(mShapeList.size - 1)
        redraw()
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

        if (mEditMode == ERASER) {
            (mDrawShape as? EraserShape)?.let {
                canvas?.drawCircle(mLastTouchX, mLastTouchY, it.indicatorRadius, it.indicatorPaint)
            }
        }
    }

    private fun resetBitmap() {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        mCanvas.setBitmap(mBitmap)
    }

    private fun redraw() {
        resetBitmap()
        setBg()
        mShapeList.forEach {
            it.draw(mCanvas)
        }
        invalidate()
    }

    private fun onMove(oldX: Float, oldY: Float, newX: Float, newY: Float) {
        mDrawShape?.move(oldX, oldY, newX, newY)
        if (mEditMode == ARROW) {
            replaceLastArrow(mDrawShape)
        } else {
            mDrawShape?.draw(mCanvas)
            invalidate()
        }
    }

    private fun onDown(x: Float, y: Float) {
        if (mDrawShape == null) {
            if (mEditMode == LINE) {
                mDrawShape = PathShape()
                mDrawShape?.changeColor(mColor)
            } else if (mEditMode == ERASER) {
                mDrawShape = EraserShape()
                mDrawShape?.changeColor(mColor)
            } else if (mEditMode == ARROW) {
                mDrawShape = ArrowShape()
                mDrawShape?.changeColor(mColor)
            }
        }
        mDrawShape?.down(x, y)
        if (mEditMode == ARROW) {
            replaceLastArrow(mDrawShape)
        } else {
            mDrawShape?.draw(mCanvas)
            invalidate()
        }
    }

    private fun onUp() {
        mDrawShape?.let {
            it.up(mLastTouchX, mLastTouchY)
            if (mEditMode != ARROW) {
                mShapeList.add(it)
            }
        }
        mDrawShape = null
        invalidate()
    }

    private fun replaceLastArrow(drawShape: DrawShape?) {
        if (mShapeList.isNotEmpty()) {
            val lastItem = mShapeList[mShapeList.size - 1]
            if (lastItem === drawShape) {
                mShapeList.remove(lastItem)
            }
        }
        if (drawShape != null)
            mShapeList.add(drawShape)
        redraw()
    }

    private interface DrawShape {
        fun down(x: Float, y: Float)
        fun move(oldX: Float, oldY: Float, newX: Float, newY: Float)
        fun up(x: Float, y: Float) {}
        fun draw(canvas: Canvas)
        fun changeColor(@ColorInt color: Int) {}
    }

    private class PathShape : DrawShape {

        private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 12f
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        private val path: Path = Path()

        override fun down(x: Float, y: Float) {
            path.moveTo(x, y)
        }

        override fun move(oldX: Float, oldY: Float, newX: Float, newY: Float) {
            path.quadTo(oldX, oldY, (newX + oldX) / 2, (newY + oldY) / 2)
        }

        override fun draw(canvas: Canvas) {
            canvas.drawPath(path, paint)
        }

        override fun changeColor(color: Int) {
            paint.color = color
        }
    }

    private class ArrowShape : DrawShape {

        private val minStrokeWidth = 1f
        private val maxStrokeWidth = 12f
        private var initialX: Float = -1f
        private var initialY: Float = -1f

        private val linePath = Path()
        private val linePaint = Paint().apply {
            style = Paint.Style.STROKE
        }
        private val trianglePath = Path()
        private val trianglePaint = Paint().apply {
            style = Paint.Style.FILL
        }

        override fun down(x: Float, y: Float) {
            if (initialX < 0 && initialY < 0) {
                initialX = x
                initialY = y
            }
            drawArrow(x, y)
        }

        override fun move(oldX: Float, oldY: Float, newX: Float, newY: Float) {
            drawArrow(newX, newY)
        }

        private fun drawArrow(newX: Float, newY: Float) {
            val distanceX = newX - initialX
            val distanceY = newY - initialY
            val lineLength = sqrt(distanceX * distanceX + distanceY * distanceY)
            val strokeWidth = min(max(lineLength * 0.05f, minStrokeWidth), maxStrokeWidth)

            linePaint.strokeWidth = strokeWidth
            linePath.reset()
            linePath.moveTo(initialX, initialY)
            val x = if (distanceX <= 0) newX + 5f else newX
            val y = if (distanceY <= 0) newY + 5f else newY
            linePath.lineTo(x, y)

            drawArrow(initialX, initialY, x, y, trianglePath)
        }

        override fun draw(canvas: Canvas) {
            canvas.drawPath(linePath, linePaint)
            canvas.drawPath(trianglePath, trianglePaint)
        }

        override fun changeColor(color: Int) {
            linePaint.color = color
            trianglePaint.color = color
        }

        private fun drawArrow(
            initialX: Float,
            initialY: Float,
            lineEndX: Float,
            lineEndY: Float,
            path: Path
        ) {
            val radius: Float
            val triangleH: Float

            val startX = lineEndX
            val startY = lineEndY

            val x = lineEndX - initialX
            val y = lineEndY - initialY
            val d = x * x + y * y
            val r = sqrt(d)
            radius = min(max(r * 0.05f, 1f), 8f)
            triangleH = min(max(r * 0.2f, 5f), 30f)
            val endX = lineEndX + (triangleH * x / r)
            val endY = lineEndY + (triangleH * y / r)
            val xz = endX - startX
            val yz = endY - startY
            val zd = xz * xz + yz * yz
            val zr = sqrt(zd)

            path.reset()
            path.moveTo(startX, startY)
            path.lineTo((lineEndX + radius * 2 * yz / zr), (lineEndY - radius * 2 * xz / zr))
            path.lineTo(endX, endY)
            path.lineTo((startX - radius * 2 * yz / zr), (startY + radius * 2 * xz / zr))
            path.close()
        }
    }

    private class EraserShape : DrawShape {
        private val path = Path()
        private val circlePath = Path()

        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.TRANSPARENT
            style = Paint.Style.STROKE
            strokeWidth = 80f
            strokeCap = Paint.Cap.ROUND
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.parseColor("#40000000")
            style = Paint.Style.STROKE
            strokeWidth = 4f
        }

        val indicatorRadius = (paint.strokeWidth - indicatorPaint.strokeWidth) / 2f

        override fun down(x: Float, y: Float) {
            circlePath.addCircle(x, y, paint.strokeWidth / 2, Path.Direction.CW)
            path.moveTo(x, y)
        }

        override fun move(oldX: Float, oldY: Float, newX: Float, newY: Float) {
            path.quadTo(oldX, oldY, (newX + oldX) / 2, (newY + oldY) / 2)
        }

        override fun draw(canvas: Canvas) {
            paint.style = Paint.Style.STROKE
            canvas.drawPath(path, paint)
            paint.style = Paint.Style.FILL
            canvas.drawPath(circlePath, paint)
            paint.style = Paint.Style.STROKE
        }
    }
}