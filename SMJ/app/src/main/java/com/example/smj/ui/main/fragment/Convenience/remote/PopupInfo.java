package com.example.smj.ui.main.fragment.Convenience.remote;

import java.io.Serializable;

public class PopupInfo implements Serializable {

    private String title;
    private String phone;
    private String address;

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public PopupInfo(String title, String phone, String address) {
        this.title = title;
        this.phone = phone;
        this.address = address;
    }
}
