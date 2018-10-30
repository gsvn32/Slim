package com.example.lenovo.slim;

/**
 * Created by LENOVO on 10/18/2018.
 */

public class exercise {
    private String name,des;
    private Double per;
    public exercise(String name,String des,Double per){
        this.setName(name);
        this.setDes(des);
        this.setPer(per);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Double getPer() {
        return per;
    }

    public void setPer(Double per) {
        this.per = per;
    }
}
