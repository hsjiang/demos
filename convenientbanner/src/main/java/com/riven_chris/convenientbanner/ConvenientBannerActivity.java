package com.riven_chris.convenientbanner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.riven_chris.convenientbanner.banner.ConvenientBanner;
import com.riven_chris.convenientbanner.banner.holder.CBViewHolderCreator;
import com.riven_chris.convenientbanner.banner.holder.Holder;

import java.util.ArrayList;
import java.util.List;

public class ConvenientBannerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenient_banner);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        private List<String> strings = new ArrayList<>();

        public MyAdapter() {
            super();
            for (int i = 0; i < 5; i++) {
                strings.add("" + i);
            }
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ConvenientBannerActivity.this).inflate(R.layout.layout_item, null);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.cb.setPages(holder.mCBViewHolderCreator, strings);
        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ConvenientBanner cb;
        CBItemViewHolder mCBItemViewHolder;
        CBViewHolderCreator<CBItemViewHolder> mCBViewHolderCreator;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cb = (ConvenientBanner) itemView.findViewById(R.id.cb);
            mCBItemViewHolder = new CBItemViewHolder();
            mCBViewHolderCreator = new CBViewHolderCreator<CBItemViewHolder>() {
                @Override
                public CBItemViewHolder createHolder() {
                    return mCBItemViewHolder;
                }
            };
        }
    }

    private class CBItemViewHolder implements Holder<String> {
        private ImageView iv;
        private TextView tv;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_cb_item, null);
            iv = (ImageView) view.findViewById(R.id.iv);
            tv = (TextView) view.findViewById(R.id.tv);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            if (data != null) {
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.fir));
                tv.setText("position: " + position);
            }
        }
    }
}
