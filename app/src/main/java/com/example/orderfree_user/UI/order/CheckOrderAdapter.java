package com.example.orderfree_user.UI.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.order.data.CheckOrderResponseData;

import java.util.List;

public class CheckOrderAdapter extends RecyclerView.Adapter<CheckOrderAdapter.ViewHolder> {
    private List<CheckOrderResponseData> list;

    public CheckOrderAdapter(List<CheckOrderResponseData> data){
        this.list = data;
    }

    @NonNull
    @Override
    public CheckOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.item_check_order, parent, false);
        CheckOrderAdapter.ViewHolder viewHolder = new CheckOrderAdapter.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOrderAdapter.ViewHolder holder, int position) {
        holder.menuName.setText(list.get(position).getMenuName());
        holder.menuCount.setText(String.valueOf(list.get(position).getMenuCount()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        TextView menuCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.item_menu_name);
            menuCount = itemView.findViewById(R.id.item_menu_count);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
