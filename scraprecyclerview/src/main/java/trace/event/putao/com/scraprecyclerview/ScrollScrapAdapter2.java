package trace.event.putao.com.scraprecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by riven_chris on 2018/3/31.
 */

public class ScrollScrapAdapter2 extends RecyclerView.Adapter<ScrollScrapAdapter2.ItemViewHolder> {
    private Context context;
    private List<String> data;

    public ScrollScrapAdapter2(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public void addItem(String s) {
        data.add(s);
        notifyDataSetChanged();
    }

    @Override
    public ScrollScrapAdapter2.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_scroll_scrap_rv_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScrollScrapAdapter2.ItemViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
