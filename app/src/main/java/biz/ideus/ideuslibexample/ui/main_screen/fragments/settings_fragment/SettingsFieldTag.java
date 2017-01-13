package biz.ideus.ideuslibexample.ui.main_screen.fragments.settings_fragment;

import biz.ideus.ideuslibexample.R;

/**
 * Created by blackmamba on 12.12.16.
 */

public enum SettingsFieldTag {
    EMAIL(R.string.change_email),
    NAME(R.string.change_name),
    CURRENT_PASSWORD(R.string.set_current_password),
    NEW_PASSWORD(R.string.change_password),
    EMPTY_TAG(R.string.empty_tag);

    public int nameField;

    SettingsFieldTag(int nameField){
        this.nameField = nameField;

    }

    public static SettingsFieldTag getSettingsFieldTag(int state){
        for(SettingsFieldTag settingsFieldTag : SettingsFieldTag.values()) {
            if (settingsFieldTag.nameField == state) return settingsFieldTag;
        }
        return null;
    }
}
