package com.reddylabs;

import com.google.gson.annotations.SerializedName;

public class PhoneNumber {
    @SerializedName("displayNumber")
    private String displayNumber;
    @SerializedName("telephoneNumber")
    private String telephoneNumber;

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "displayNumber='" + displayNumber + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    public String getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(String displayNumber) {
        this.displayNumber = displayNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
