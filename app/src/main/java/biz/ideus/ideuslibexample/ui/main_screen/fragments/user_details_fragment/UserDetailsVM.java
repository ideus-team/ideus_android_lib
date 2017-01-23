package biz.ideus.ideuslibexample.ui.main_screen.fragments.user_details_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.chat_screen.activity.ChatActivity;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.main_screen.activity.MainActivity;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;
import biz.ideus.ideuslibexample.utils.Constants;

import static biz.ideus.ideuslibexample.utils.Constants.PEOPLE_ID;

/**
 * Created by blackmamba on 12.01.17.
 */

public class UserDetailsVM  extends AbstractViewModelToolbar<StartView>{

    private Context context;
    private String peopleId = "";

    public String getPeopleId() {
        return peopleId;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if(arguments != null){
            peopleId = arguments.getString(PEOPLE_ID);
        }

    }

    @Override
    public void onBindView(@NonNull StartView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    public void onChatClick(View view){
        Intent intent = new Intent(((MainActivity)context), ChatActivity.class);
        intent.putExtra(Constants.CHAT_PEOPLE_ID, peopleId);
        ((MainActivity)context).startActivity(intent);

    }


    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.details);
    }
}
