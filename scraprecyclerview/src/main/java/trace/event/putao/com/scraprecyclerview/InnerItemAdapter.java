package trace.event.putao.com.scraprecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by riven_chris on 2018/3/26.
 */

public class InnerItemAdapter extends RecyclerView.Adapter<InnerItemAdapter.ItemViewHolder> {
    private Context mContext;

    public InnerItemAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_inner_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv.setText(String.valueOf(position));
        if (position % 2 > 0) {
            holder.rvSub.setVisibility(View.VISIBLE);
            SubItemAdapter adapter = (SubItemAdapter) holder.rvSub.getAdapter();
            if (adapter == null) {
                adapter = new SubItemAdapter();
                holder.rvSub.setAdapter(adapter);
            }
        } else {
            holder.rvSub.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private RecyclerView rvSub;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);

            rvSub = (RecyclerView) itemView.findViewById(R.id.rv_sub);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.VERTICAL, false);
            rvSub.setLayoutManager(manager);
        }
    }
}
