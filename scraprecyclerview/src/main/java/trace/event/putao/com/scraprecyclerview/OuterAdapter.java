package trace.event.putao.com.scraprecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by riven_chris on 2018/3/26.
 */

public class OuterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public OuterAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 > 0) ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
        return viewType == 1 ? new InnerVerticalHolder(view) : new InnerGridHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 1) {
            InnerVerticalHolder itemHolder = (InnerVerticalHolder) holder;
            InnerItemAdapter adapter = (InnerItemAdapter) itemHolder.rvInnerVertical.getAdapter();
            if (adapter == null) {
                adapter = new InnerItemAdapter(mContext);
                itemHolder.rvInnerVertical.setAdapter(adapter);
            }

        } else {
            InnerGridHolder itemHolder = (InnerGridHolder) holder;
            InnerItemAdapter adapter = (InnerItemAdapter) itemHolder.rvInnerGrid.getAdapter();
            if (adapter == null) {
                adapter = new InnerItemAdapter(mContext);
                itemHolder.rvInnerGrid.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class InnerVerticalHolder extends RecyclerView.ViewHolder {

        private RecyclerView rvInnerVertical;

        public InnerVerticalHolder(View itemView) {
            super(itemView);
            rvInnerVertical = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rvInnerVertical.setLayoutManager(linearLayoutManager);
        }
    }

    public static class InnerGridHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvInnerGrid;

        public InnerGridHolder(View itemView) {
            super(itemView);
            rvInnerGrid = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(), 3);
            gridLayoutManager.setAutoMeasureEnabled(true);
            rvInnerGrid.setLayoutManager(gridLayoutManager);
        }
    }
}
