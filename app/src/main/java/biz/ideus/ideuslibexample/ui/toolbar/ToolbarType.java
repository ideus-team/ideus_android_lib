package biz.ideus.ideuslibexample.ui.toolbar;

public enum ToolbarType {

    DEFAULT(new ToolbarStandard()),
    BACK_BTN_TOOLBAR(new ToolbarStandardBackBtn());

//    DOCTOR_TOOLBAR(new ToolbarDoctor(false)),
//    DOCTOR_TOOLBAR_BACK_BTN(new ToolbarDoctor(true));

    private ToolbarStandard toolbarSettingsPower;

    ToolbarType(ToolbarStandard toolbarSettingsPower) {
        this.toolbarSettingsPower = toolbarSettingsPower;
    }

    public ToolbarStandard getToolbarSettingsPower() {
        return toolbarSettingsPower.instantiate();
    }
}
