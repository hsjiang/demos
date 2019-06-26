package trace.event.putao.com.scraprecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ScrapRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rvOuter;

    private OuterAdapter mOuterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_recycler_view);
        rvOuter = (RecyclerView) findViewById(R.id.rv_outer);
        LinearLayoutManager outerManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvOuter.setLayoutManager(outerManager);
        mOuterAdapter = new OuterAdapter(this);
        rvOuter.setAdapter(mOuterAdapter);
    }
}
