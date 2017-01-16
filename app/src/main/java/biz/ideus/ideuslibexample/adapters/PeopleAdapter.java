package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.databinding.ItemPeopleBinding;

/**
 * Created by blackmamba on 16.01.17.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleItemHolder> {


    private List<PeopleEntity> peopleEntities = new ArrayList<>();
    private OnPeopleClickListener onPeopleClickListener;

    public void setOnPeopleClickListener(OnPeopleClickListener onPeopleClickListener) {
        this.onPeopleClickListener = onPeopleClickListener;
    }

    public PeopleAdapter() {

    }

    public void setPeopleEntities(List<PeopleEntity> peopleEntities){
        this.peopleEntities = peopleEntities;
        notifyDataSetChanged();
    }

    public List<PeopleEntity> getPeopleEntities() {
        return peopleEntities;
    }

    @Override
    public PeopleAdapter.PeopleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PeopleAdapter.PeopleItemHolder(DataBindingUtil.inflate(inflater,
                R.layout.item_people, parent, false).getRoot());

    }

    @Override
    public void onBindViewHolder(PeopleAdapter.PeopleItemHolder holder, int position) {
        PeopleEntity peopleEntity = peopleEntities.get(position);
        holder.binding.setViewModel(peopleEntity);
        holder.binding.getRoot().setOnClickListener(v -> onPeopleClickListener.onClickItem(position, peopleEntity));
    }


    @Override
    public int getItemCount() {
        return peopleEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class PeopleItemHolder extends RecyclerView.ViewHolder {
        public ItemPeopleBinding binding;

        public PeopleItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

    public interface OnPeopleClickListener{
        void onClickItem(int position, PeopleEntity peopleEntity);
    }

}
