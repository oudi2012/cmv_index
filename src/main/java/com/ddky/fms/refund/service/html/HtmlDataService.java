package com.ddky.fms.refund.service.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/***
 * 互联网数据
 * @author a
 */
public interface HtmlDataService {

    /***
     * 根据地址获取页面内容
     * @param url
     * @return
     */
    default Document getContent(String url) {
        try {
            return Jsoup.connect(url).get();
        }catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

}
