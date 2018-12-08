package com.reddylabs;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class PhoneNumbers {

    PhoneNumber[] phoneNumbers ;

    public PhoneNumber[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumber[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "PhoneNumbers{" +
                "phoneNumbers=" + Arrays.toString(phoneNumbers) +
                '}';
    }

}
