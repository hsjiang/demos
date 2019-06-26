package trace.event.putao.com.scraprecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ScrollScrapRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rv1;
    private RecyclerView rv2;

    private ScrollScrapAdapter1 mAdapter1;
    private ScrollScrapAdapter2 mAdapter2;

    private List<String> data1 = new ArrayList<>();
    private List<String> data2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_scrap_recycler_view);

        for (int i = 0; i < 10; i++) {
            String str = String.valueOf(i);
            data1.add(str);
            data2.add(str);
        }

        rv1 = (RecyclerView) findViewById(R.id.rv1);
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv1.setLayoutManager(manager1);
        rv1.setHasFixedSize(true);
        rv1.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter1 = new ScrollScrapAdapter1(this, data1);
        rv1.setAdapter(mAdapter1);

        rv2 = (RecyclerView) findViewById(R.id.rv2);
        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(manager2);
        mAdapter2 = new ScrollScrapAdapter2(this, data2);
        rv2.setAdapter(mAdapter2);
    }

    public void add1(View view) {
        int index = mAdapter1.getItemCount();
        mAdapter1.addItem(String.valueOf(index));
    }

    public void add2(View view) {
        int index = mAdapter2.getItemCount();
        mAdapter2.addItem(String.valueOf(index));
    }
}
