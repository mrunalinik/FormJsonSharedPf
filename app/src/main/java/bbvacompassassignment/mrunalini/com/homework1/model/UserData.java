package bbvacompassassignment.mrunalini.com.homework1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mrunalinikoritala on 1/10/18.
 */

public class UserData implements Parcelable{
    private String userName;
    private String email;
    private String phoneNo;
    private String gender;
    private String country;

    public UserData(String userName, String email, String phoneNo, String gender, String country) {
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.country = country;
    }

    public UserData() {
    }

    protected UserData(Parcel in) {
        userName = in.readString();
        email = in.readString();
        phoneNo = in.readString();
        gender = in.readString();
        country = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(email);
        parcel.writeString(phoneNo);
        parcel.writeString(gender);
        parcel.writeString(country);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
