package com.sy.feature_categorize.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sy.feature_categorize.bean.ResCategory;
import com.sy.feature_categorize.databinding.ItemLeftCategory1Binding;
import com.sy.feature_categorize.databinding.ItemRightCategory2Binding;
import com.sy.feature_categorize.databinding.ItemRightImageBinding;

import java.util.ArrayList;
import java.util.List;

public class CategorySecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CategorySecondAdapter";
    private List<ResCategory.ResFirstCategory.ResSecondCategory> mDatas =new ArrayList<>();
    private ItemCategorySecondListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRightCategory2Binding binding = ItemRightCategory2Binding.inflate(inflater, parent, false);
        return new CategorySecondViewHoldel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategorySecondViewHoldel viewhoder =(CategorySecondViewHoldel)holder;
        viewhoder.binding.setCategorySecond(mDatas.get(position));

        /**
         * 不要在 ViewHolder 构造函数中调用 getLayoutPosition()，因为此时位置尚未确定,输出的值为-1
         * 在 onBindViewHolder()中 或 用户交互事件中 获取位置才是正确的时机
         * 将第三级分类的数据设置移到 onBindViewHolder() 中处理
         */
        viewhoder.categoryThirdAdapter.setDatas(mDatas.get(position).getChildren());
        Log.i(TAG, "onBindViewHolder: position " + position); // 这里会输出正确的位置

    }

    @Override
    public int getItemCount() {
        int count = mDatas.isEmpty() ? 0 : mDatas.size();
        Log.i(TAG, "getItemCount: "+mDatas.isEmpty()+count);
        return count;
    }

    public void setDatas(List<ResCategory.ResFirstCategory.ResSecondCategory> resSecondCategories) {
        if (!resSecondCategories.isEmpty()) {
            mDatas = resSecondCategories;
            Log.i(TAG, "onChanged: 分类数据"+mDatas.isEmpty()+mDatas.size());
        }
        //改变适配器的数据一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定要刷新
        notifyDataSetChanged();
    }

    public class CategorySecondViewHoldel extends RecyclerView.ViewHolder {
        private final ItemRightCategory2Binding binding;
        private final CategoryThirdAdapter categoryThirdAdapter; // 将适配器作为成员变量

        public CategorySecondViewHoldel(ItemRightCategory2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // 通过binding.getRoot()获取上下文
            Context context = binding.getRoot().getContext();
            binding.rvCategory3.setLayoutManager(new GridLayoutManager(context,3));
            categoryThirdAdapter = new CategoryThirdAdapter();
            binding.rvCategory3.setAdapter(categoryThirdAdapter);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getLayoutPosition();
                }
            });
        }
    }

    public void setmListener(ItemCategorySecondListener mListener) {
        this.mListener = mListener;
    }

    public interface ItemCategorySecondListener {

        void onItemClick(List<ResCategory.ResFirstCategory.ResSecondCategory.ResThirdCategory> ThirdCategory);

    }

}
