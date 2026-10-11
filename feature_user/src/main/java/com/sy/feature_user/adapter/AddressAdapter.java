package com.sy.feature_user.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sy.libbase.bean.ResAddress;
import com.sy.feature_user.databinding.ItemAddressBinding;
import com.sy.feature_user.databinding.ItemAddressDefaultBinding;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AddressAdapter";
    public static final int ITEM_TYPE_DEFAULT = 1; //默认收货地址
    public static final int ITEM_TYPE_NORMAL = 2; //普通收货地址
    private List<ResAddress> mDatas;
    private ItemHotsaleListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType==ITEM_TYPE_DEFAULT){
            ItemAddressDefaultBinding binding = ItemAddressDefaultBinding.inflate(inflater, parent, false);
            return new AddressDefaultViewHolder(binding);
        }else {
            ItemAddressBinding binding = ItemAddressBinding.inflate(inflater, parent, false);
            return new AddressViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType==ITEM_TYPE_DEFAULT){
            AddressDefaultViewHolder viewhoder = (AddressDefaultViewHolder) holder;
            ItemAddressDefaultBinding binding = viewhoder.binding;
            binding.provinceCity.setText(mDatas.get(position).getProvince_name()+" " +mDatas.get(position).getCity_name()+" " +mDatas.get(position).getDistrict_name());
            binding.tvDetailedAddress.setText(mDatas.get(position).getAddress());
            binding.namePhome.setText(mDatas.get(position).getConsignee()+" "+mDatas.get(position).getMobile());
        }else {
            AddressViewHolder viewhoder = (AddressViewHolder) holder;
            ItemAddressBinding binding = viewhoder.binding;
            binding.tvProvinceCity.setText(mDatas.get(position).getProvince_name()+" " +mDatas.get(position).getCity_name()+" " +mDatas.get(position).getDistrict_name());
            binding.tvDetaildeAddress2.setText(mDatas.get(position).getAddress());
            binding.tvNamePhone.setText(mDatas.get(position).getConsignee()+" "+mDatas.get(position).getMobile());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position).isIs_default()) {
            return ITEM_TYPE_DEFAULT;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setDatas(List<ResAddress> resAddress) {
        mDatas = resAddress;
        notifyDataSetChanged();
    }

    // ViewHolder：用ViewBinding绑定Item布局
    public class AddressViewHolder extends RecyclerView.ViewHolder {
        ItemAddressBinding binding;

        public AddressViewHolder(ItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    mListener.onItemClick(mDatas.get(position));
                }
            });
        }
    }
    public class AddressDefaultViewHolder extends RecyclerView.ViewHolder {
        ItemAddressDefaultBinding binding;

        public AddressDefaultViewHolder(ItemAddressDefaultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    mListener.onItemClick(mDatas.get(position));
                }
            });
        }
    }

    public void setmListener(ItemHotsaleListener mListener) {
        this.mListener = mListener;
    }

    public interface ItemHotsaleListener {

        void onItemClick(ResAddress selectAddress);

    }
}
