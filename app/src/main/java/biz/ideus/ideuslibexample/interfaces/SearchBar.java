package biz.ideus.ideuslibexample.interfaces;

import android.view.View;

/**
 * Created by blackmamba on 05.12.16.
 */

public interface SearchBar {
   void onTextChangedSearch(CharSequence text, int start, int before, int count);
   void onCancelClick(View view);

}
