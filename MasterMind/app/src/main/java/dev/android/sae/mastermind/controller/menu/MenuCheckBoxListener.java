package dev.android.sae.mastermind.controller.menu;

import android.widget.CompoundButton;

import dev.android.sae.mastermind.model.menu.ModelMenu;

public class MenuCheckBoxListener implements CompoundButton.OnCheckedChangeListener {

    private ModelMenu model;

    public MenuCheckBoxListener(ModelMenu model){
        this.model = model;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        this.model.setEmptyPawnActivated(b);
    }
}
