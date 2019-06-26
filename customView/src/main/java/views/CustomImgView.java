package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.example.riven_chris.myapplication.R;

/**
 * Created by riven_chris on 15/5/1.
 */
public class CustomImgView extends View {

    private Bitmap bitmap = null;

    public CustomImgView(Context context) {
        super(context);
    }

    public CustomImgView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.minv);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultWidth = 0;

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY) {
            resultWidth = sizeWidth;
        } else {
            resultWidth = bitmap.getWidth() + getPaddingLeft() + getPaddingRight();

            if (modeWidth == MeasureSpec.AT_MOST)
                resultWidth = Math.min(resultWidth, sizeWidth);
        }


        int resultHeight = 0;

        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = bitmap.getHeight() + getPaddingTop() + getPaddingBottom();

            if (modeHeight == MeasureSpec.AT_MOST)
                resultHeight = Math.min(resultHeight, sizeHeight);
        }

        setMeasuredDimension(resultWidth, resultHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, getPaddingTop(), getPaddingLeft(), null);
    }
}
