package it.marcosoft.ticketwave.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;


public class User implements Parcelable {
    private String name;
    private String surname;
    private int age;
    private String email;

    private String idToken;

    public User(){

    }

    public User(String name, String email, String idToken) {
        this.name = name;
        this.email = email;
        this.idToken = idToken;
        this.surname="unknown";
        this.age=0;

    }

    public User(String name, String email, String surname, int age, String idToken) {
        this.name = name;
        this.surname=surname;
        this.age=age;
        this.email = email;
        this.idToken = idToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname() {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = age;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Exclude
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", idToken='" + idToken + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.idToken);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.email = source.readString();
        this.idToken = source.readString();


    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.idToken = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
