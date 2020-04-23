package views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

val TAG_EVENT_ACTION = "event_action"

class PaintToolView : View {

    private val INVALID_POINTER = -1
    private val MIN_MOVE_DIS = 5

    private var mPointerId = INVALID_POINTER
    private var mInitialTouchX = 0f
    private var mInitialTouchY = 0f
    private var mLastTouchX = 0f
    private var mLastTouchY = 0f

    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPath: Path? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    init {
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 15f
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeJoin = Paint.Join.ROUND
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
                    Log.d(TAG_EVENT_ACTION, "ACTION_DOWN mPointerId:${mPointerId}")
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    mPointerId = getPointerId(actionIndex)
                    mLastTouchX = getX(actionIndex)
                    mLastTouchY = getY(actionIndex)
                    Log.d(TAG_EVENT_ACTION, "ACTION_POINTER_DOWN mPointerId:${mPointerId}")
                }
                MotionEvent.ACTION_MOVE -> {
                    val index: Int = findPointerIndex(mPointerId)
                    if (index > 0) return true

                    if (abs(x - mLastTouchX) >= MIN_MOVE_DIS || abs(y - mLastTouchY) >= MIN_MOVE_DIS) {
                        drawPath(x, y)
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
                    }
                    Log.d(TAG_EVENT_ACTION, "ACTION_POINTER_UP mPointerId:${mPointerId}")
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
                    mPath == null
                    val mPointerId = getPointerId(actionIndex)
                    Log.d(TAG_EVENT_ACTION, "ACTION_UP mPointerId:${mPointerId}")
                }
                else -> return true
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPath?.let {
            canvas?.drawPath(it, mPaint)
        }
    }

    private fun drawPath(x: Float, y: Float) {
        if (mPath == null) {
            mPath = Path()
            mPath?.moveTo(x, y)
        } else {
            mPath?.quadTo(mLastTouchX, mLastTouchY, (x + mLastTouchX) / 2, (y + mLastTouchY) / 2)
        }
        invalidate()
    }
}