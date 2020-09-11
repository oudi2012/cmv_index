package com.ddky.fms.refund.utils;

import com.ddky.fms.refund.model.books.wyw.entry.ArticleInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/***
 * @author oudi
 */
public class HtmlUtils {

    /**
     * 格式化html
     */
    public static List<ArticleInfo> formatHtmlList(String url) {
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements typeConts = document.getElementsByClass("typecont");
            for (Element typeCont: typeConts) {
                Elements spanList = typeCont.getElementsByTag("span");
                for (Element span: spanList) {
                    ArticleInfo articleInfo = new ArticleInfo();
                    Element aEl = span.child(0);
                    articleInfo.setHref(aEl.attr("href"));
                    articleInfo.setTitle(aEl.ownText());
                    if (!StringUtils.isEmpty(span.ownText())) {
                        articleInfo.setAuthor(span.ownText().replace("(","").replace(")",""));
                    }
                    articleInfoList.add(articleInfo);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return articleInfoList;
    }

    /**
     * 格式化html
     */
    public static ArticleInfo formatHtmlCont(ArticleInfo articleInfo) {
        try {
            Document document = Jsoup.connect(articleInfo.getHref()).get();
            String href = articleInfo.getHref();
            href = href.substring(href.lastIndexOf("_") + 1);
            href = href.substring(0, href.indexOf("."));
            String contsonId = "contson" + href;
            //System.out.println(document.toString());
            Element typeCont = document.getElementById(contsonId);
            if (typeCont != null) {
                articleInfo.setCont(typeCont.html()
                        .replaceAll("<p>","")
                        .replaceAll("</p>","")
                        .replaceAll("<br>","")
                        .trim());
            }
            System.out.println(articleInfo.getCont());
        } catch (IOException io) {
            io.printStackTrace();
        }
        return articleInfo;
    }

    //https://so.gushiwen.org/gushi/xishi.aspx
    //https://so.gushiwen.org/gushi/xiaoxue.aspx
    public static void main(String[] args)  {
        ArticleInfo articleInfo = new ArticleInfo();
        //https://so.gushiwen.org/shiwenv_26f3e66fe495.aspx
        articleInfo.setHref("https://so.gushiwen.org/shiwenv_4998e6425431.aspx");
        formatHtmlCont(articleInfo);
    }
}
