package com.android.testmvp.network.bean.image;

public class ImageBean {

    /**
     * success : true
     * imgurl : https://cache.4ce.cn/star3/origin/9785a8e7085264b413f401a1f4aeb8ce.png
     * info : {"width":2400,"height":1080,"type":"img"}
     */

    private boolean success;
    private String imgurl;
    private InfoBean info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }
}
