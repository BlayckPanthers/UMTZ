package com.ingesup.docblayck.umtz.Entities;

/**
 * Created by Najib on 26/02/2018.
 */

public class Service {
    private int host_id;
    private String descritpion;
    private int service_id;
    private int state;
    private String output;
    private double last_check;
    private double last_state_change;

    public Service(int host_id, String descritpion, int service_id, int state, String output, double last_check, double last_state_change) {
        this.host_id = host_id;
        this.descritpion = descritpion;
        this.service_id = service_id;
        this.state = state;
        this.output = output;
        this.last_check = last_check;
        this.last_state_change = last_state_change;
    }

    public int getHost_id() {
        return host_id;
    }

    public void setHost_id(int host_id) {
        this.host_id = host_id;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public double getLast_check() {
        return last_check;
    }

    public void setLast_check(double last_check) {
        this.last_check = last_check;
    }

    public double getLast_state_change() {
        return last_state_change;
    }

    public void setLast_state_change(double last_state_change) {
        this.last_state_change = last_state_change;
    }
}
