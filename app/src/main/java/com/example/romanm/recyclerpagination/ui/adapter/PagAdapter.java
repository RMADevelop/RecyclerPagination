package com.example.romanm.recyclerpagination.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romanm.recyclerpagination.data.Item;
import com.example.romanm.recyclerpagination.R;
import com.example.romanm.recyclerpagination.ui.RecyclerListener;

import java.util.ArrayList;
import java.util.List;


public class PagAdapter extends RecyclerView.Adapter<PagAdapter.PagViewHolder> {

    List<Item> newList = new ArrayList<>();
    List<Item> oldList = new ArrayList<>();


    List<Item> lastList;
    List<Item> middleList;
    List<Item> firstList;

    RecyclerListener listener;


    public PagAdapter(List<Item> lastList, List<Item> middleList, List<Item> firstList, RecyclerListener listener) {
        this.lastList = lastList;
        this.middleList = middleList;
        this.firstList = firstList;

        this.listener = listener;

        containToMainList(lastList, middleList, firstList);
    }

    @Override
    public PagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pag_recycler, parent, false);
        return new PagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PagViewHolder holder, int position) {
        holder.bindTo(newList.get(position));
        listener.itemBind(position, getItemCount());

    }


    @Override
    public int getItemCount() {
        return newList.size();
    }

    public void setFirstList(List<Item> list) {
        oldList = newList;

        lastList = middleList;
        middleList = firstList;
        firstList = list;
//        lastList = middleList;
//        middleList = firstList;
//        firstList = list;

        containToMainList(lastList, middleList, firstList);
        Log.v("ASDSAD","size " + list.get(1).getId());
        notifyDataSetChanged();
//        DiffUtillRecycler diffUtillRecycler = new DiffUtillRecycler(oldList, newList);
//        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffUtillRecycler,true);
//        result.dispatchUpdatesTo(this);

    }

    public void setFirstWithoutNonify(List<Item> list) {
        oldList = newList;

        lastList = middleList;
        middleList = firstList;
        firstList = list;
//        lastList = middleList;
//        middleList = firstList;
//        firstList = list;

        containToMainList(lastList, middleList, firstList);
    }

    public void setLastList(List<Item> list) {
        firstList = middleList;
        middleList = lastList;
        lastList = list;

        containToMainList(lastList, middleList, firstList);
    }

    private void containToMainList(List<Item> last, List<Item> middle, List<Item> first) {
//        newList.clear();
//
//        newList.addAll(last);
//        newList.addAll(middle);
        newList.addAll(first);


    }

    public void addAll(List<Item> list) {
        newList.clear();
        newList.addAll(list);
        notifyDataSetChanged();
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
