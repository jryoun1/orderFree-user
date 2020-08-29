package com.example.orderfree_user.UI.mainview.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.mainview.data.MenuData;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private List<MenuData> list=new ArrayList<>();

    public MenuAdapter(List<MenuData> list) {
        this.list = list;
    }

    public interface OnItemClickListner{
        void onItemClick(View v,int pos);
    }

    private OnItemClickListner mListner =null;

    public void setOnItemClickListner(OnItemClickListner listener){
        this.mListner=listener;
    }


    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.menudata_layout,parent,false);
        MenuAdapter.MenuViewHolder viewHolder = new MenuAdapter.MenuViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.textView_menu.setText(list.get(position).getmenuName());
        holder.textView_price.setText(String.valueOf(list.get(position).getmenuPrice()));
    }

    @Override
    public int getItemCount() {
        return (list!=null ? list.size() : 0);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView textView_menu;
        TextView textView_price;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView_menu=itemView.findViewById(R.id.textView_menu);
            this.textView_price=itemView.findViewById(R.id.textView_price);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), com.example.orderfree_user.UI.mainview.activity.SelectMenuActivity.class);
                    intent.putExtra("menuName",list.get(pos).getmenuName());
                    intent.putExtra("storeName",list.get(0).getOwnerStoreName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}