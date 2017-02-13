package biz.ideus.ideuslibexample.ui.main_screen.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.adapters.BoardsAdapter;
import biz.ideus.ideuslibexample.ui.listeners.ScrollListenerForFAB;


public class MainActivity extends AbstractMainActivity {

    private BoardsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setModelView(this);

        adapter = new BoardsAdapter();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getBinding().rViewBoards.setHasFixedSize(true);
        getBinding().rViewBoards.setLayoutManager(linearLayoutManager);
        getBinding().rViewBoards.setAdapter(adapter);
        getViewModel().setAdapter(adapter);
        getBinding().rViewBoards.addOnScrollListener(new ScrollListenerForFAB(getBinding().fabAddBoard));
    }


    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

}
