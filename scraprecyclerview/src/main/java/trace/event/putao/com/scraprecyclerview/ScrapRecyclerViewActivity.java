package trace.event.putao.com.scraprecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScrapRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rvOuter;

    private OuterAdapter mOuterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_recycler_view);
        rvOuter =  findViewById(R.id.rv_outer);
        LinearLayoutManager outerManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvOuter.setLayoutManager(outerManager);
        mOuterAdapter = new OuterAdapter(this);
        rvOuter.setAdapter(mOuterAdapter);
    }
}
