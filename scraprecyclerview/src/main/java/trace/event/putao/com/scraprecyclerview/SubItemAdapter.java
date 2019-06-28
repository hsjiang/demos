package trace.event.putao.com.scraprecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by riven_chris on 2018/3/26.
 */

public class SubItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sub_item, parent, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {
        public SubItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
