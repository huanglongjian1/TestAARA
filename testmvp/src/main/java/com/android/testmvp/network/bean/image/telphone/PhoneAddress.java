package com.android.testmvp.network.bean.image.telphone;

public class PhoneAddress {

    /**
     * success : true
     * tel : 13145211010
     * info : {"local":"江苏省徐州市","duan":"1314521","type":"江苏联通GSM卡","yys":"中国联通","bz":"GSM (全球移动通信系统)"}
     */

    private boolean success;
    private String tel;
    private InfoBean info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }
}
