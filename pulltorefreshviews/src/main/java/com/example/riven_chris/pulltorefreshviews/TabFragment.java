package com.example.riven_chris.pulltorefreshviews;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import pullrefresh.PullToRefreshListView;

public class TabFragment extends Fragment
{
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private PullToRefreshListView mListView;
    // private TextView mTextView;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sticky_header, container, false);
        mListView = (PullToRefreshListView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        // mTextView = (TextView) view.findViewById(R.id.id_info);
        // mTextView.setText(mTitle);
        for (int i = 0; i < 50; i++)
        {
            mDatas.add(mTitle+" -> " + i);
        }
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.layout_pull_list_item, R.id.id_info, mDatas)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                //Log.e("tag", "convertView = " + convertView);
                return super.getView(position, convertView, parent);
            }
        });
        return view;

    }

    public static TabFragment newInstance(String title)
    {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

}
