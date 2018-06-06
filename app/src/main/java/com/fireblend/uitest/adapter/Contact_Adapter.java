package com.fireblend.uitest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireblend.uitest.R;
import com.fireblend.uitest.entities.Contact;

import java.util.List;

/**
 * Created by acamacho on 4/6/2018.
 */

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ContatcsViewHolder> {

    List<Contact> myContacts;
    RecyclerView recyclerView;
    Context mContext;
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
        holder.name.setText(myContacts.get(position).getName());
        holder.age.setText(String.valueOf(myContacts.get(position).getAge()));
        holder.phone.setText(myContacts.get(position).getPhone());
        holder.email.setText(myContacts.get(position).getEmail());
        holder.provincia.setText(myContacts.get(position).getProvincia());

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
