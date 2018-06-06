package com.fireblend.uitest.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fireblend.uitest.R;
import com.fireblend.uitest.adapter.Contact_Adapter;
import com.fireblend.uitest.entities.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity {

    private MainActivity mActivity;
    RecyclerView list;
    Contact_Adapter adapter;
    ArrayList<Contact> contacts;


    //@BindView(R.id.lvError)
    RelativeLayout relativeLayout;




    @Override
    @Optional
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(savedInstanceState != null){
            contacts = savedInstanceState.getParcelableArrayList("contacts");
        }else{
            contacts = getIntent().getParcelableArrayListExtra("contacts");
        }



        relativeLayout = (RelativeLayout) findViewById(R.id.lvError);
        list = (RecyclerView)findViewById(R.id.lista_contactos);
        list.setHasFixedSize(true);
        list.setItemViewCacheSize(5);
        list.setDrawingCacheEnabled(true);
        list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        setupList(contacts == null || contacts.size() == 0 ? true: false);

    }

    private void setupList(boolean itsEmpty) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()) {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {

                    private static final float SPEED = 300f; // la velocidad en que se hace el movimiento

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }

                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };




        if(itsEmpty){
            contacts = new ArrayList();
            relativeLayout.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
            //Lista ejemplo con datos estaticos. Normalmente, estos serían recuperados de una BD o un REST API.
            //contacts.add(new Contact("Sergio", 28, "sergiome@gmail.com", "88854764", "San Jose"));
            //contacts.add(new Contact("Andres", 1, "alex@gmail.com", "88883644", "San Jose"));
           // contacts.add(new Contact("Andrea", 2, "andrea@gmail.com", "98714764", "San Jose"));
           //.add(new Contact("Fabian", 3, "fabian@gmail.com", "12345678", "San Jose"));
           // contacts.add(new Contact("Ivan", 4, "ivan@gmail.com", "87654321", "San Jose"));
           // contacts.add(new Contact("Gabriela", 5, "gabriela@gmail.com", "09871234", "San Jose"));
           // contacts.add(new Contact("Alex", 6, "sergiome@gmail.com", "43215678", "San Jose"));
        }else{
            relativeLayout.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }


        list.setLayoutManager(layoutManager);
        adapter = new Contact_Adapter(contacts,list,getApplicationContext());
        list.setAdapter(adapter);


    }

    @Optional
    @OnClick(R.id.goToNext)
    public void goToAadd(){
        Intent intent = new Intent(this, ManageContactActivity.class );
        intent.putParcelableArrayListExtra("contacts",contacts);
        startActivity(intent);
        finish();
    }


    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
        saveState.putParcelableArrayList("contacts", (ArrayList<? extends Parcelable>) contacts);

    }



}
