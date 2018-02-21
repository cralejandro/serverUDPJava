package model;

public class Rele {

    private int id;
    private String name;
    private String description;
    private byte state;
    private String macWemos;

    public Rele(int id, String name, String description, byte state, String macWemos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.macWemos = macWemos;
    }


    public Rele() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getMacWemos() {
        return macWemos;
    }

    public void setMacWemos(String macWemos) {
        this.macWemos = macWemos;
    }
}
