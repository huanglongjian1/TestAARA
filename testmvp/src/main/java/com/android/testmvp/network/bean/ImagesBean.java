package com.android.testmvp.network.bean;

import java.util.List;

public class ImagesBean {
    /**
     * startdate : 20230508
     * fullstartdate : 202305081600
     * enddate : 20230509
     * url : /th?id=OHR.Atoll_ZH-CN9469093805_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp
     * urlbase : /th?id=OHR.Atoll_ZH-CN9469093805
     * copyright : 卡韦希环礁，土阿莫土群岛，法属波利尼西亚 (© WaterFrame/Alamy)
     * copyrightlink : https://www.bing.com/search?q=%E5%8D%A1%E9%9F%A6%E5%B8%8C%E7%8E%AF%E7%A4%81&form=hpcapt&mkt=zh-cn
     * title : 天堂的一角
     * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20230508_Atoll%22&FORM=HPQUIZ
     * wp : true
     * hsh : 49c854e335b5260866d7ed122fd580ae
     * drk : 1
     * top : 1
     * bot : 1
     * hs : []
     */

    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String url;
    private String urlbase;
    private String copyright;
    private String copyrightlink;
    private String title;
    private String quiz;
    private boolean wp;
    private String hsh;
    private int drk;
    private int top;
    private int bot;
    private List<?> hs;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getFullstartdate() {
        return fullstartdate;
    }

    public void setFullstartdate(String fullstartdate) {
        this.fullstartdate = fullstartdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public void setUrlbase(String urlbase) {
        this.urlbase = urlbase;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightlink() {
        return copyrightlink;
    }

    public void setCopyrightlink(String copyrightlink) {
        this.copyrightlink = copyrightlink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public boolean isWp() {
        return wp;
    }

    public void setWp(boolean wp) {
        this.wp = wp;
    }

    public String getHsh() {
        return hsh;
    }

    public void setHsh(String hsh) {
        this.hsh = hsh;
    }

    public int getDrk() {
        return drk;
    }

    public void setDrk(int drk) {
        this.drk = drk;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBot() {
        return bot;
    }

    public void setBot(int bot) {
        this.bot = bot;
    }

    public List<?> getHs() {
        return hs;
    }

    public void setHs(List<?> hs) {
        this.hs = hs;
    }

    @Override
    public String toString() {
        return "ImagesBean{" +
                "startdate='" + startdate + '\'' +
                ", fullstartdate='" + fullstartdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", url='" + url + '\'' +
                ", urlbase='" + urlbase + '\'' +
                ", copyright='" + copyright + '\'' +
                ", copyrightlink='" + copyrightlink + '\'' +
                ", title='" + title + '\'' +
                ", quiz='" + quiz + '\'' +
                ", wp=" + wp +
                ", hsh='" + hsh + '\'' +
                ", drk=" + drk +
                ", top=" + top +
                ", bot=" + bot +
                ", hs=" + hs +
                '}';
    }
}
