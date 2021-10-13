package com.example.nlushop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;

import java.util.List;

public class AdapterNoiBat extends RecyclerView.Adapter<AdapterNoiBat.ViewHolder> {
    Context context;
    List<String> stringList;

    public AdapterNoiBat(Context context, List<String> stringList){
        this.context = context;
        this.stringList = stringList;
    }

    //Hàm chạy thứ 2, sau khi hàm onCreateViewHolder() trả kết quả
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //lúc này itemview truyền vào là view trong cái hàm đầu tiên
            textView = itemView.findViewById(R.id.txtTieuDeNoiBat);
        }
    }

    @NonNull
    @Override
    //Hàm chạy đầu tiên, khởi tạo view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recycleview_noibat, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Hàm chạy thứ 3
    @Override
    //ViewHolder này nhận từ ViewHolder từ hàm chạy thứ 2
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

}
