package com.android.testmvp.network.bean;

import java.util.List;

public class Bean {

    /**
     * images : [{"startdate":"20230508","fullstartdate":"202305081600","enddate":"20230509","url":"/th?id=OHR.Atoll_ZH-CN9469093805_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","urlbase":"/th?id=OHR.Atoll_ZH-CN9469093805","copyright":"卡韦希环礁，土阿莫土群岛，法属波利尼西亚 (© WaterFrame/Alamy)","copyrightlink":"https://www.bing.com/search?q=%E5%8D%A1%E9%9F%A6%E5%B8%8C%E7%8E%AF%E7%A4%81&form=hpcapt&mkt=zh-cn","title":"天堂的一角","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20230508_Atoll%22&FORM=HPQUIZ","wp":true,"hsh":"49c854e335b5260866d7ed122fd580ae","drk":1,"top":1,"bot":1,"hs":[]}]
     * tooltips : {"loading":"正在加载...","previous":"上一个图像","next":"下一个图像","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}
     */

    private TooltipsBean tooltips;
    private List<ImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "tooltips=" + tooltips +
                ", images=" + images +
                '}';
    }
}
