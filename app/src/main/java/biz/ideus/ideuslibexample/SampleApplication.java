package biz.ideus.ideuslibexample;

import biz.ideus.ideuslib.adapter.typeface_adapters.DLibTypefaceAdapter;
import biz.ideus.ideuslib.application.DLibApplication;

/**
 * Created by user on 09.11.2016.
 */

public class SampleApplication extends DLibApplication {
    @Override
    protected void setupFonts() {
        DLibTypefaceAdapter.addFontDefinition("normal", "fonts/MuseoSansCyrl.otf");
    }
    
}
