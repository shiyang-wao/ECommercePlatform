package com.sy.feature_categorize.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sy.feature_categorize.bean.ResCategory;
import com.sy.feature_categorize.databinding.ItemLeftCategory1Binding;
import com.sy.feature_categorize.databinding.ItemRightCategory3Binding;

import java.util.ArrayList;
import java.util.List;

public class CategoryThirdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CategoryThirdAdapter";
    private List<ResCategory.ResFirstCategory.ResSecondCategory.ResThirdCategory> mDatas =new ArrayList<>();
    private ItemCategoryFirstListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRightCategory3Binding binding = ItemRightCategory3Binding.inflate(inflater, parent, false);
        return new CategoryThirdViewHoldel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryThirdViewHoldel viewhoder =(CategoryThirdViewHoldel)holder;
        viewhoder.binding.setCategoryThrid(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        int count = mDatas.isEmpty() ? 0 : mDatas.size();
        Log.i(TAG, "getItemCount: "+mDatas.isEmpty()+count);
        return count;
    }

    public void setDatas(List<ResCategory.ResFirstCategory.ResSecondCategory.ResThirdCategory> resThirdCategories) {
        if (!resThirdCategories.isEmpty()) {
            mDatas = resThirdCategories;
            Log.i(TAG, "onChanged: 分类数据"+mDatas.isEmpty()+mDatas.size());
        }
        //改变适配器的数据一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定要刷新
        notifyDataSetChanged();
    }

    public class CategoryThirdViewHoldel extends RecyclerView.ViewHolder {
        private final ItemRightCategory3Binding binding;

        public CategoryThirdViewHoldel(ItemRightCategory3Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getLayoutPosition();
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
