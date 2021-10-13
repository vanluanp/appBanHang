package com.example.nlushop.model.objectClass;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreScroll extends RecyclerView.OnScrollListener{
    int itemAnDauTien = 0;
    int tongItem = 0;
    int itemLoadTruoc = 10;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    //Xử lý khi vuốt
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongItem = layoutManager.getItemCount();

        //Kiểm tra đang thuộc layout nào, sau đó tìm itemAnDauTien, chính là số item đã vuốt qua rồi
        if(layoutManager instanceof LinearLayoutManager){
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else {
            if(layoutManager instanceof GridLayoutManager){
                itemAnDauTien = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }
        }

        //Nếu load đến số item quy định thì tiếp tục load dữ liệu, mỗi lần là tongItem dữ liệu
        if(tongItem <= (itemAnDauTien + itemLoadTruoc)){
//            Log.d("kiemtra", tongItem + " - " + itemAnDauTien);
            iLoadMore.loadMore(tongItem);

        }

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
