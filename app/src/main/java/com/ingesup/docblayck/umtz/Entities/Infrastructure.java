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

    public Infrastructure(String serverName, String serverIp, int serverStatus){
        this.server_name = serverName;
        this.server_ip = serverIp;
        this.server_status = serverStatus;
    }

    protected Infrastructure(Parcel in) {
        server_name = in.readString();
        server_ip = in.readString();
        server_status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(server_name);
        dest.writeString(server_ip);
        dest.writeInt(server_status);
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
}
