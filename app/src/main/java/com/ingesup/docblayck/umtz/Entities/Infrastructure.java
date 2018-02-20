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
    private int server_warning;
    private int server_error;
    private int server_unkwown;

    public Infrastructure(String server_name, String server_ip, int server_status, int server_warning, int server_error, int server_unkwown) {
        this.server_name = server_name;
        this.server_ip = server_ip;
        this.server_status = server_status;
        this.server_warning = server_warning;
        this.server_error = server_error;
        this.server_unkwown = server_unkwown;
    }

    protected Infrastructure(Parcel in) {
        server_name = in.readString();
        server_ip = in.readString();
        server_status = in.readInt();
        server_warning = in.readInt();
        server_error = in.readInt();
        server_unkwown = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(server_name);
        dest.writeString(server_ip);
        dest.writeInt(server_status);
        dest.writeInt(server_warning);
        dest.writeInt(server_error);
        dest.writeInt(server_unkwown);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getServer_warning() {
        return server_warning;
    }

    public void setServer_warning(int server_warning) {
        this.server_warning = server_warning;
    }

    public int getServer_error() {
        return server_error;
    }

    public void setServer_error(int server_error) {
        this.server_error = server_error;
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
