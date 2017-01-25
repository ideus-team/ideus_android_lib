package biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.base.BaseActivity;

import android.view.ViewGroup;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.databinding.ItemLatestConnectionsBinding;

import biz.ideus.ideuslibexample.ui.main_screen.fragments.BaseSearchVM;

/**
 * Created by blackmamba on 25.11.16.
 */

public class HomeFragmentVM extends BaseSearchVM {

    HomeAdapter homeAdapter = new HomeAdapter();

    public HomeAdapter getAdapter() {
        return homeAdapter;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        //BuildConfig.APPLICATION_ID
    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.home);
    }



    @Override
    public void onTextChangedSearch(CharSequence text, int start, int before, int count) {
        Log.d("CharSequence", text.toString());
    }


    @Override
    public void onCancelClick(View view) {
        ((BaseActivity) context).hideKeyboard();
        visibilitySearch.set(View.GONE);
        isFocus.set(false);

    }


    private class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }
    }

    private static class ItemLatestConnectionsHolder extends RecyclerView.ViewHolder {
        ItemLatestConnectionsBinding binding;

        ItemLatestConnectionsHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

}

