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
import com.example.orderfree_user.UI.mainview.data.ConfirmOrderData;

import java.util.ArrayList;
import java.util.List;


public class ConfirmOrderAdapter extends RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder>{

    private List<ConfirmOrderData> list=new ArrayList<>();

    public ConfirmOrderAdapter(List<ConfirmOrderData> list) {
        this.list = list;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListner =null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListner=listener;
    }


    @NonNull
    @Override
    public ConfirmOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.confirmorderdata_layout,parent,false);
        ConfirmOrderAdapter.ViewHolder viewHolder = new ConfirmOrderAdapter.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_menu.setText(list.get(position).getmenuName());
        holder.textView_count.setText(String.valueOf(list.get(position).getCount()));
        holder.textView_price.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return (list!=null ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_menu;
        TextView textView_price;
        TextView textView_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView_menu=itemView.findViewById(R.id.textView_menu);
            this.textView_price=itemView.findViewById(R.id.price_menu);
            this.textView_count=itemView.findViewById(R.id.count_menu);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(),DeleteShoppingLIstPopupActivity.class);
                    intent.putExtra("userEmail","user2");
                    intent.putExtra("storeName","김치남");
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

}