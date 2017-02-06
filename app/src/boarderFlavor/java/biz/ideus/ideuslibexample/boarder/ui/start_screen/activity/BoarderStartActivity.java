package biz.ideus.ideuslibexample.boarder.ui.start_screen.activity;

import android.os.Bundle;
import android.widget.Toast;

import biz.ideus.ideuslibexample.ui.start_screen.activity.StartActivity;

public class BoarderStartActivity extends StartActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().setStartActivityInterface(() -> {
            Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
        });
    }
}
