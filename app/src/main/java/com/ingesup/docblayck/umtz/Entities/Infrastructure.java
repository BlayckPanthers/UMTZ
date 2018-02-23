package com.ingesup.docblayck.umtz.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fabienlebon on 16/02/2018.
 */

public class Infrastructure implements Parcelable{

    private String server_name;
    private String server_ip;
    private int server_status;
    private int server_ok;
    private int server_warning;
    private int server_critical;
    private int server_unkwown;

    public Infrastructure(String server_name, String server_ip, int server_status, int server_ok, int server_warning, int server_critical, int server_unkwown) {
        this.server_name = server_name;
        this.server_ip = server_ip;
        this.server_status = server_status;
        this.server_ok = server_ok;
        this.server_warning = server_warning;
        this.server_critical = server_critical;
        this.server_unkwown = server_unkwown;
    }

    protected Infrastructure(Parcel in) {
        server_name = in.readString();
        server_ip = in.readString();
        server_status = in.readInt();
        server_ok = in.readInt();
        server_warning = in.readInt();
        server_critical = in.readInt();
        server_unkwown = in.readInt();
    }

    public static final Creator<Infrastructure> CREATOR = new Creator<Infrastructure>() {
        @Override
        public Infrastructure createFromParcel(Parcel in) {
            return new Infrastructure(in);
        }

        @Override
        public Infrastructure[] newArray(int size) {
            return new Infrastructure[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(server_name);
        parcel.writeString(server_ip);
        parcel.writeInt(server_status);
        parcel.writeInt(server_ok);
        parcel.writeInt(server_warning);
        parcel.writeInt(server_critical);
        parcel.writeInt(server_unkwown);
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public int getServer_status() {
        return server_status;
    }

    public void setServer_status(int server_status) {
        this.server_status = server_status;
    }

    public int getServer_ok() {
        return server_ok;
    }

    public void setServer_ok(int server_ok) {
        this.server_ok = server_ok;
    }

    public int getServer_warning() {
        return server_warning;
    }

    public void setServer_warning(int server_warning) {
        this.server_warning = server_warning;
    }

    public int getServer_critical() {
        return server_critical;
    }

    public void setServer_critical(int server_critical) {
        this.server_critical = server_critical;
    }

    public int getServer_unkwown() {
        return server_unkwown;
    }

    public void setServer_unkwown(int server_unkwown) {
        this.server_unkwown = server_unkwown;
    }

    public static Creator<Infrastructure> getCREATOR() {
        return CREATOR;
    }
}
