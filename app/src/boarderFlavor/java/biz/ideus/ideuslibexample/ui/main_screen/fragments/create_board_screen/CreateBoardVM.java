package biz.ideus.ideuslibexample.ui.main_screen.fragments.create_board_screen;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import biz.ideus.ideuslibexample.R;
import biz.ideus.ideuslibexample.ui.common.toolbar.AbstractViewModelToolbar;
import biz.ideus.ideuslibexample.ui.main_screen.BoardMainView;

/**
 * Created by blackmamba on 09.02.17.
 */

public class CreateBoardVM  extends AbstractViewModelToolbar<BoardMainView>  {

    protected Context context;
    public ObservableField<String> boardName = new ObservableField<>();

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        boardName.set("");
    }



    @Override
    public void onBindView(@NonNull BoardMainView view) {
        super.onBindView(view);
        context = view.getViewModelBindingConfig().getContext();
    }

    public void onTextChangedBoardName(CharSequence text, int start, int before, int count) {
        boardName.set(text.toString());
    }

    public void onCreateBoardClick(){

    }

//    private void createBoard(){
//        webSocketClient.sendMessage(new CreateBoardRequest("board name test"));
//    }
//    private void updateBoard(){
//        webSocketClient.sendMessage(new UpdateBoardRequest("board name test update", "bordId"));
//    }

    @Override
    public String getToolbarTitle() {
        return context.getString(R.string.create_new_board);
    }

}

