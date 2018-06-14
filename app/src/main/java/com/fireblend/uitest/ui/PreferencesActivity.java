package com.fireblend.uitest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fireblend.uitest.R;
import com.fireblend.uitest.helpers.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class PreferencesActivity extends AppCompatActivity {

    private final int HOME = 16908332;
    MaterialDialog materialDialog;
    PreferencesManager preferencesManager;

    @BindView(R.id.txtSize)
    TextView txtSize;

    @BindView(R.id.backC)
    TextView backC;

    @BindView(R.id.cols123)
    TextView cols123;


    @BindView(R.id.simpleSwitch)
    Switch simpleSwitch;

    int textSize;
    String color;
    boolean cheked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        ButterKnife.bind(this);
        preferencesManager = PreferencesManager.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textSize = preferencesManager.getIntValue(getApplicationContext(),preferencesManager.ARG_FONTSIZE);
        color = preferencesManager.getStringValue(getApplicationContext(),preferencesManager.ARG_BACKGROUNDCOLOR2);
        cheked = preferencesManager.getBoolean(getApplicationContext(),preferencesManager.ARG_SHOWHIDE);
        backC.setText(color);
        simpleSwitch.setChecked(cheked);

        if(textSize == 1){
            textSize = 14;
        }

        if(color.equals("")){
            backC.setText("Blanco");
        }

        txtSize.setText(textSize +"sp");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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


    @OnClick(R.id.rlSizeChange)
    public void changeSizeSp(){
        chooseData("Cambiar tamaño de Fuente",R.array.sizes,1);
    }


    @OnClick(R.id.backGrounfR)
    public void changeBC(){
        chooseData("Color de Fondo",R.array.colors,2);
    }

    @OnClick(R.id.columns)
    public void changeColsList(){
        chooseData("Visualización", R.array.vis,3);
    }



    @OnCheckedChanged(R.id.simpleSwitch)
    public void changeDelete(boolean checked){
        preferencesManager.saveBoolean(getApplicationContext(),preferencesManager.ARG_SHOWHIDE,checked);
    }



    public void chooseData(String title, final int array, final int id){
        materialDialog = new MaterialDialog.Builder(this)
                .title(title)
                .items(array)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if(which != -1){
                            switch (id){
                                case 1 :
                                    methoCaseFont(which);
                                    return true;
                                case 2:
                                    methoCaseBC(which);
                                    return true;
                                case 3:
                                    methoCaseCols(which == 0 ? 1 : 2);
                                    return true;
                                default : return false;
                            }
                        }
                        return false;
                    }
                })
                .positiveText(R.string.choose)
                .show();
    }


    //metodo del fontSize
    public void methoCaseFont(int i){
        int[] mTestArray = getResources().getIntArray(R.array.sizesint);
        preferencesManager.saveInt(getApplicationContext(),preferencesManager.ARG_FONTSIZE,mTestArray[i]);
        txtSize.setText(mTestArray[i] + "sp");
    }

    //metodo del fontSize
    public void methoCaseBC(int i){
        String[] mTestArray = getResources().getStringArray(R.array.colorshex);
        String[] mTestArray2 = getResources().getStringArray(R.array.colors);
        preferencesManager.saveString(getApplicationContext(),preferencesManager.ARG_BACKGROUNDCOLOR,mTestArray[i]);
        preferencesManager.saveString(getApplicationContext(),preferencesManager.ARG_BACKGROUNDCOLOR2,mTestArray2[i]);
        backC.setText(mTestArray2[i]);
    }

    public void methoCaseCols(int i){
        preferencesManager.saveInt(getApplicationContext(),preferencesManager.ARG_COLUMNS,i);
        cols123.setText(i == 1? "Lista" : "Columnas");
    }





}
