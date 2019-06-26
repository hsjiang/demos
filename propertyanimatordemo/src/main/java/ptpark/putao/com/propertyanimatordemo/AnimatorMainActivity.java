package ptpark.putao.com.propertyanimatordemo;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AnimatorMainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        iv = (ImageView) findViewById(R.id.iv);

        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fab, "scaleX", 1.f, 2.f);
        ObjectAnimator.ofArgb(false, "", 1111, 111);
        PropertyValuesHolder holder;
//                    holder.setPropertyName();
        TypeConverter converter;
        TimeInterpolator interpolator;
        new AccelerateDecelerateInterpolator();
        new LinearInterpolator();


        TypeEvaluator<Integer> evaluator = new TypeEvaluator<Integer>() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return 0;
            }
        };

        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ObjectAnimator argbAnimator = ObjectAnimator.ofObject(iv, "backgroundColor", new ArgbEvaluator(),
                getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));
        argbAnimator.setDuration(5000);
        argbAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        argbAnimator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animator_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
