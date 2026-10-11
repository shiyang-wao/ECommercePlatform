package com.sy.feature_categorize.adapter;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sy.feature_categorize.bean.ResCategory;
import com.sy.feature_categorize.databinding.ItemLeftCategory1Binding;

import java.util.ArrayList;
import java.util.List;

public class CategoryFirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CategoryFirstAdapter";
    private List<ResCategory.ResFirstCategory> mDatas =new ArrayList<>();
    private ItemCategoryFirstListener mListener;
    private int selectedPosition = 0; // 初始选中项是第一个

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemLeftCategory1Binding binding = ItemLeftCategory1Binding.inflate(inflater, parent, false);
        return new CategoryFirstViewHoldel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryFirstViewHoldel viewhoder =(CategoryFirstViewHoldel)holder;
        viewhoder.binding.setCategoryfirst(mDatas.get(position));
        // 根据是否选中设置背景
        if (position == selectedPosition) {
            viewhoder.binding.ivBg.setVisibility(View.VISIBLE);
        } else {
            viewhoder.binding.ivBg.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        int count = mDatas.isEmpty() ? 0 : mDatas.size();
        Log.i(TAG, "getItemCount: "+mDatas.isEmpty()+count);
        return count;
    }

    public void setDatas(List<ResCategory.ResFirstCategory> resFirstCategories) {
        if (!resFirstCategories.isEmpty()) {
            mDatas = resFirstCategories;
            Log.i(TAG, "onChanged: 分类数据"+mDatas.isEmpty()+mDatas.size());
        }
        //改变适配器的数据一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定要刷新
        notifyDataSetChanged();
    }

    public class CategoryFirstViewHoldel extends RecyclerView.ViewHolder {
        private final ItemLeftCategory1Binding binding;

        public CategoryFirstViewHoldel(ItemLeftCategory1Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getLayoutPosition();
                    // 更新选中位置
                    int previousSelected = selectedPosition;
                    selectedPosition = position;
                    // 刷新前一个选中项和当前选中项
                    notifyItemChanged(previousSelected);
                    notifyItemChanged(selectedPosition);

                    List<ResCategory.ResFirstCategory.ResSecondCategory> secondCategory = mDatas.get(position).getChildren();
                    String image = mDatas.get(position).getImage(); //由于图片数据在一级分类里，但是又不是一级分类的item，只能在外部处理图片的显示
                    mListener.onItemClick(secondCategory,image);
                }
            });
        }
    }

    public void setmListener(ItemCategoryFirstListener mListener) {
        this.mListener = mListener;
    }

    public interface ItemCategoryFirstListener {

        void onItemClick(List<ResCategory.ResFirstCategory.ResSecondCategory> secondCategory,String image);

    }

}
