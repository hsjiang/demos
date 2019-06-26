package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by riven_chris on 15/4/23.
 */
public class CustomPathEffectView extends View {

    private Paint paint = null;
    private Path mPath = null;
    private PathEffect[] pathEffects = null;
    private float phase = 0;

    public CustomPathEffectView(Context context) {
        this(context, null);
    }

    public CustomPathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
        mPath.moveTo(0, 0);

        for (int i = 0; i <= 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

//        path = new Path();

        pathEffects = new PathEffect[7];
//        for (int i = 0; i < pathEffects.length; i++) {
//
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        pathEffects[0] = null;
        pathEffects[1] = new CornerPathEffect(50);
        pathEffects[2] = new DiscretePathEffect(9.0F, 15.0F);
//        pathEffects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);
        pathEffects[3] = new DashPathEffect(new float[]{20, 10, 20, 10, 5, 10}, phase);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CW);
//        path.addCircle(0, 0, 4, Path.Direction.CCW);
        pathEffects[4] = new PathDashPathEffect(path, 12, phase, PathDashPathEffect.Style.MORPH);
        pathEffects[5] = new ComposePathEffect(pathEffects[2], pathEffects[4]);
//        pathEffects[5] = new ComposePathEffect(pathEffects[4], pathEffects[2]);
        pathEffects[6] = new SumPathEffect(pathEffects[4], pathEffects[3]);

        for (int i = 0; i < pathEffects.length; i++) {
            paint.setPathEffect(pathEffects[i]);
            canvas.drawPath(mPath, paint);
            canvas.translate(0, 180);
        }

        phase += 1;
        invalidate();
    }
}
