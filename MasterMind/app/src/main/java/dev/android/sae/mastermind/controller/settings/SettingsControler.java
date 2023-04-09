package dev.android.sae.mastermind.controller.settings;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

public class SettingsControler implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Activity settingActivity;

    public SettingsControler(Activity settingActivity){
        this.settingActivity = settingActivity;
    }

    @Override
    public void onClick(View view) {
        //On évite de recreer un objet intent alors que l'intent de setiingActivity peut etre utilisé (celui récuperé via getIntent, le meme que le paramètre de settingActivity.onCreate)
        this.settingActivity.setResult(this.settingActivity.RESULT_OK, this.settingActivity.getIntent());
        Log.d("tag", "fin = "+this.settingActivity.getIntent().getBooleanExtra("empty_pawns",false));
        this.settingActivity.finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        this.settingActivity.getIntent().putExtra("empty_pawns",b);
        Log.d("tag","changement falg = "+this.settingActivity.getIntent().getBooleanExtra("empty_pawns",false));
    }
}
