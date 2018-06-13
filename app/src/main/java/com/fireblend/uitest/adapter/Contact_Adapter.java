package com.fireblend.uitest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireblend.uitest.R;
import com.fireblend.uitest.Utils.Utils;
import com.fireblend.uitest.entities.Contact;

import java.util.List;

/**
 * Created by acamacho on 4/6/2018.
 */

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ContatcsViewHolder> {

    List<Contact> myContacts;
    RecyclerView recyclerView;
    Context mContext;
    private int textSize;
    private String color = "#fff";
    public Contact_Adapter(List<Contact> contacts, RecyclerView recyclerView, Context context){
        myContacts = contacts;
        this.recyclerView = recyclerView;
        this.mContext = context;
    }

    @Override
    public ContatcsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ContatcsViewHolder pvh = new ContatcsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ContatcsViewHolder holder, int position) {
        holder.name.setText(myContacts.get(position).name);
        holder.age.setText(String.valueOf(myContacts.get(position).age));
        holder.phone.setText(myContacts.get(position).phone);
        holder.email.setText(myContacts.get(position).email);
        holder.provincia.setText(myContacts.get(position).provincia);

        holder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        holder.age.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        holder.phone.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        holder.email.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        holder.provincia.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);


        holder.name.setBackgroundColor(Color.parseColor(color));
        holder.age.setBackgroundColor(Color.parseColor(color));
        holder.phone.setBackgroundColor(Color.parseColor(color));
        holder.email.setBackgroundColor(Color.parseColor(color));
        holder.provincia.setBackgroundColor(Color.parseColor(color));


    }


    public void setTextSizes(int textSize) {
        this.textSize = textSize;
        notifyDataSetChanged();
    }

    public void setColor(String COLOR) {
        this.color = COLOR;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return myContacts.size();
    }

    public static class ContatcsViewHolder extends RecyclerView.ViewHolder {


        TextView name, age, phone, email, provincia;

        ContatcsViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            phone = (TextView) itemView.findViewById(R.id.phone);
            email = (TextView) itemView.findViewById(R.id.email);
            provincia = (TextView) itemView.findViewById(R.id.provincia);
        }
    }
}
