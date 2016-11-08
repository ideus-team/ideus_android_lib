package biz.ideus.ideuslib.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.trello.rxlifecycle.components.RxActivity;

/**
 * Created by user on 08.11.2016.
 */

public abstract class DLibBindingActivity extends RxActivity {
    private Fragment oldFragment;


    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ImageLoader.getInstance().isInited()) {
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        }
    }

    public abstract int getLayoutId();

    public abstract void onInit(View rootView);

    protected View onInit(LayoutInflater inflater, ViewGroup viewGroup) {
        return inflater.inflate(getLayoutId(), viewGroup, false);
    }


    public void addFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in
                , android.R.animator.fade_out
                , android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void addImageViewerFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in
                , android.R.animator.fade_out
                , android.R.animator.fade_in, android.R.animator.fade_out)
                .add(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void addDialogFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in
                , android.R.animator.fade_out
                , android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.replace(android.R.id.content, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.commit();
        oldFragment = fragment;
    }

    public void addReviewFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in
                , android.R.animator.fade_out
                , android.R.animator.fade_in, android.R.animator.fade_out)
                .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    public Fragment getTopFragment() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        FragmentManager.BackStackEntry backEntry = getFragmentManager()
                .getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1);
        String str = backEntry.getName();
        return getFragmentManager().findFragmentByTag(str);
    }

    public void removeFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .remove(fragment)
                .commit();
        getFragmentManager().popBackStack();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View view = getCurrentFocus();
        if (view != null && (motionEvent.getAction() == MotionEvent.ACTION_UP
                || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
                && view instanceof EditText && !view.getClass().getName()
                .startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = motionEvent.getRawX() + view.getLeft() - scrcoords[0];
            float y = motionEvent.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight()
                    || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow((this.getWindow().getDecorView()
                                .getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

}