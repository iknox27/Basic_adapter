package com.fireblend.uitest.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

/**
 * Created by Sergio on 8/20/2017.
 */

public class Contact implements Parcelable {
    //Clase entidad para contener cada elemento de la lista, el cual representa un Contacto.
    private String name;
    private int age;
    private String email;
    private String phone;
    private String provincia;

    public Contact(String name, int age, String email, String phone,String provincia){
        this.setName(name);
        this.setAge(age);
        this.setEmail(email);
        this.setPhone(phone);
        this.setProvincia(provincia);
    }


    protected Contact(Parcel in) {
        name = in.readString();
        age = in.readInt();
        email = in.readString();
        phone = in.readString();
        provincia = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(provincia);
    }
}
