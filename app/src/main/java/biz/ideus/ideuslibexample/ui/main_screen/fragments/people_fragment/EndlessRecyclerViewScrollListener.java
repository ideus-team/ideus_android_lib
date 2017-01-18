package biz.ideus.ideuslibexample.ui.main_screen.fragments.people_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import biz.ideus.ideuslibexample.adapters.PeopleAdapter;

/**
 * Created by blackmamba on 17.01.17.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerViewScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private static boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;
    private PeopleAdapter adapter;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager linearLayoutManager, PeopleAdapter adapter) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.adapter = adapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            visibleItemCount = mLinearLayoutManager.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (adapter.getItemCount() <= visibleItemCount + pastVisiblesItems) {
                    loading = false;
                    onLoadMore(totalItemCount);
                }
            }
        }
    }

    public abstract void onLoadMore(int current_page);


    public static void setLoaded(boolean loadedFlag) {
        loading = loadedFlag;
    }

    public static boolean isLoading() {
        return loading;
    }

}