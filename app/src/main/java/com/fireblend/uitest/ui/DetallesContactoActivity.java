package com.fireblend.uitest.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.fireblend.uitest.R;
import com.fireblend.uitest.Utils.Utils;
import com.fireblend.uitest.entities.Contact;
import com.fireblend.uitest.helpers.DataBaseManager;
import com.fireblend.uitest.helpers.PreferencesManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetallesContactoActivity extends AppCompatActivity {
    private final int HOME = 16908332;
    MaterialDialog materialDialog;
    PreferencesManager preferencesManager;
    private static final long SPLASH_SCREEN_DELAY = 3000;
    DataBaseManager dataBaseManager;
    @BindView(R.id.nameDetail)
    TextView nameDetail;
    @BindView(R.id.emailD)
    TextView emailD;
    @BindView(R.id.ageDET)
    TextView ageDET;
    @BindView(R.id.numDeta)
    TextView numDeta;
    @BindView(R.id.provD)
    TextView provD;

    @BindView(R.id.buttonDel)
    Button buttonDel;

    @BindView(R.id.descargar)
    Button descargar;

    int userid;
    boolean aparecer;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_contacto);
        ButterKnife.bind(this);
        preferencesManager = PreferencesManager.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        utils = new Utils(this);
        dataBaseManager = DataBaseManager.getInstance();
        dataBaseManager.startHelper(getApplicationContext());
        aparecer= preferencesManager.getBoolean(getApplicationContext(),preferencesManager.ARG_SHOWHIDE);
        if(savedInstanceState == null){
            nameDetail.setText(getIntent().getExtras().getString("name"));
            ageDET.setText(getIntent().getExtras().getString("age"));
            numDeta.setText( getIntent().getExtras().getString("phone"));
            emailD.setText(getIntent().getExtras().getString("email"));
            provD.setText(getIntent().getExtras().getString("provincia"));
            userid = getIntent().getExtras().getInt("userId");
        }
        buttonDel.setVisibility(aparecer? View.VISIBLE : View.GONE);
    }


    @OnClick(R.id.buttonDel)
    public void descargar(){

    }

    @OnClick(R.id.buttonDel)
    public void eliminar(){
        materialDialog = new MaterialDialog.Builder(this)
                .title("Eliminar Contacto")
                .content("Realmente desea eliminar el Contacto " + nameDetail.getText().toString())
                .positiveText("Eliminar")
                .negativeText("Cancelar")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        utils.showProgess("Eliminando Usuario");
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                dataBaseManager.deleteContact(userid);
                                Intent i = new Intent(DetallesContactoActivity.this,MainActivity.class);
                                utils.hideProgress();
                                startActivity(i);
                                finish();
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, SPLASH_SCREEN_DELAY);
                    }

                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        materialDialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d("id", String.valueOf(item.getItemId()));

        switch (item.getItemId()) {
            case HOME:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
