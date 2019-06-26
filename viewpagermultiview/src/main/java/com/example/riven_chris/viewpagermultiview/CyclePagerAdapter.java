package com.example.riven_chris.viewpagermultiview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by riven_chris on 2015/10/27.
 */
public class CyclePagerAdapter extends PagerAdapter {

    private Context context;

    private int[] data = new int[]{R.drawable.iv1, R.drawable.iv2, R.drawable.iv3,
            R.drawable.iv4, R.drawable.iv5, R.drawable.iv6,};

    private SparseArray<View> views = new SparseArray<>();

    public CyclePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.d("PagerAdapter", "getCount");
        return data.length + 2;
    }

    public int getRealCount() {
        return data.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPos = toRealPosition(position);
        View view = null;
        if (views.get(realPos) == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_pager_item, null);
        } else {
            view = views.get(realPos);
            views.remove(realPos);
        }
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        iv.setImageResource(data[realPos]);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView iv = (ImageView) object;
        container.removeView(iv);
        int realPos = toRealPosition(position);
        views.put(realPos, iv);
    }

    int toRealPosition(int position) {
        int realCount = data.length;
        if (realCount == 0)
            return 0;
        int realPosition = (position - 1) % realCount;
        if (realPosition < 0)
            realPosition += realCount;

        return realPosition;
    }
}
