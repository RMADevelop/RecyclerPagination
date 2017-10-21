package com.example.romanm.recyclerpagination.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romanm.recyclerpagination.Item;
import com.example.romanm.recyclerpagination.R;

import java.util.List;


public class PagAdapter extends RecyclerView.Adapter<PagAdapter.PagViewHolder> {

    List<Item> mainList;

    @Override
    public PagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pag_recycler, parent, false);
        return new PagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PagViewHolder holder, int position) {
        holder.bindTo(mainList.get(position));
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    static class PagViewHolder extends RecyclerView.ViewHolder {
        TextView number;

        public PagViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.text_item);
        }

        public void bindTo(Item item) {
            number.setText(String.valueOf(item.getId()));

        }
    }
}
