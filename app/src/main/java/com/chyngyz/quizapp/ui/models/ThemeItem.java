package com.chyngyz.quizapp.ui.models;

public class ThemeItem {
    private int iconDrawableId;
    private boolean isChange;

    public ThemeItem(int iconDrawableId, boolean isChange) {
        this.iconDrawableId = iconDrawableId;
        this.isChange = isChange;
    }

    public int getIconDrawableId() {
        return iconDrawableId;
    }

    public void setIconDrawableId(int iconDrawableId) {
        this.iconDrawableId = iconDrawableId;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}
