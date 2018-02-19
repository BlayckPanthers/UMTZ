package com.ingesup.docblayck.umtz;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by fabienlebon on 16/02/2018.
 */

public class Infrastructure {

    private String server_name;
    private String server_ip;
    private int server_status;

    public Infrastructure(String serverName, String serverIp, int serverStatus){
        this.server_name = serverName;
        this.server_ip = serverIp;
        this.server_status = serverStatus;
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

}
