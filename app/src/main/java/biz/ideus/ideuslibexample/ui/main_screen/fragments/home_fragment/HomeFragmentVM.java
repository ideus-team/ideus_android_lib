package biz.ideus.ideuslibexample.ui.main_screen.fragments.home_fragment;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.UUID;

import biz.ideus.ideuslib.mvvm_lifecycle.AbstractViewModel;
import biz.ideus.ideuslibexample.ui.start_screen.StartView;

/**
 * Created by blackmamba on 25.11.16.
 */

public class HomeFragmentVM extends AbstractViewModel<StartView> {

    public final ObservableField<String> text = new ObservableField<>("00");


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        text.set(UUID.randomUUID().toString());
    }
}

