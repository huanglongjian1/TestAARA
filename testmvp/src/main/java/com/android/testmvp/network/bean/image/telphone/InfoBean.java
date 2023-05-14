package com.android.testmvp.network.bean.image.telphone;

public class InfoBean {
    /**
     * local : 江苏省徐州市
     * duan : 1314521
     * type : 江苏联通GSM卡
     * yys : 中国联通
     * bz : GSM (全球移动通信系统)
     */

    private String local;
    private String duan;
    private String type;
    private String yys;
    private String bz;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDuan() {
        return duan;
    }

    public void setDuan(String duan) {
        this.duan = duan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYys() {
        return yys;
    }

    public void setYys(String yys) {
        this.yys = yys;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "local='" + local + '\'' +
                ", duan='" + duan + '\'' +
                ", type='" + type + '\'' +
                ", yys='" + yys + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}
