package trace.event.putao.com.scraprecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ScrapTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_test);
    }

    public void toScrapRecyclerView(View view) {
        startActivity(new Intent(this, ScrapRecyclerViewActivity.class));
    }

    public void toScrollScrapRecyclerView(View view) {
        startActivity(new Intent(this, ScrollScrapRecyclerViewActivity.class));
    }
}
