package model;

import java.util.ArrayList;

public class Message {

    private String mac;
    private ArrayList<Rele> reles;


    public Message(String mac) {
        this.mac = mac;
        this.reles = new ArrayList<Rele>();
    }

    public Message() {
        reles=new ArrayList<Rele>();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public ArrayList<Rele> getReles() {
        return reles;
    }

    public void setReles(ArrayList<Rele> reles) {
        this.reles = reles;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();

        for (int i=0;i<reles.size();i++) {
            sb.append(i);
            sb.append(":");
            sb.append(reles.get(0).getState());
            if(i!=reles.size()-1){
                sb.append(",");
            }
        }


        return sb.toString();
    }
}
