package biz.ideus.ideuslibexample.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.databinding.ItemPeopleBinding;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;

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

    public void setPeopleEntities(Set<PeopleEntity> peopleEntities) {
        this.peopleEntities = new ArrayList<>(peopleEntities);
        notifyDataSetChanged();
    }

    public void changeFavouriteStatus(int position) {
        PeopleEntity changingPeopleEntity = peopleEntities.get(position);
        if (changingPeopleEntity.isFavorite()) {
            changeFavouritePeople(false, changingPeopleEntity);
        } else {
            changeFavouritePeople(true, changingPeopleEntity);
        }
        notifyItemChanged(position);
    }

    private void changeFavouritePeople(boolean isFavourite, PeopleEntity changingPeople) {
        changingPeople.setFavorite(isFavourite);
        requeryApi.updatePeopleEntityById(changingPeople);
    }


    @Override
    public PeopleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PeopleItemHolder(DataBindingUtil.inflate(inflater,
                R.layout.item_people, parent, false).getRoot());
    }


    @Override
    public void onBindViewHolder(PeopleItemHolder holder, int position) {
        PeopleEntity peopleEntity = peopleEntities.get(position);
        holder.binding.setViewModel(peopleEntity);
        holder.binding.imageViewCircle.loadImage(peopleEntity.getPhoto());
        holder.binding.favouritesLayout.setOnClickListener(v -> onPeopleClickListener.onClickFavourite(position, peopleEntity));
        holder.binding.rlMain.setOnClickListener(v -> onPeopleClickListener.onClickItem(position, peopleEntity));
    }


    @Override
    public int getItemCount() {
        return peopleEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class PeopleItemHolder extends RecyclerView.ViewHolder {
        public ItemPeopleBinding binding;

        public PeopleItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

    public interface OnPeopleClickListener {
        void onClickItem(int position, PeopleEntity peopleEntity);

        void onClickFavourite(int position, PeopleEntity peopleEntity);
    }

}
