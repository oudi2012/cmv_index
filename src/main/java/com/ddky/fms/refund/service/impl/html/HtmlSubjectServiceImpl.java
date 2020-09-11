package com.ddky.fms.refund.service.impl.html;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.model.books.config.ElectiveEnum;
import com.ddky.fms.refund.model.books.config.VolumeEnum;
import com.ddky.fms.refund.model.books.entry.BookInfo;
import com.ddky.fms.refund.model.books.entry.VersionType;
import com.ddky.fms.refund.model.students.entry.GradeInfo;
import com.ddky.fms.refund.service.*;
import com.ddky.fms.refund.service.book.BookInfoService;
import com.ddky.fms.refund.service.book.SubjectInfoService;
import com.ddky.fms.refund.service.html.HtmlDataService;
import com.ddky.fms.refund.service.html.HtmlSubjectService;
import com.ddky.fms.refund.service.image.DownLoadImageService;
import com.ddky.fms.refund.service.student.GradeService;
import com.ddky.fms.refund.utils.HttpUtil2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 科目
 * @Author wanghaikuo
 * @Date 2020/7/6
 **/
@Service
public class HtmlSubjectServiceImpl implements HtmlSubjectService, HtmlDataService {

    private static final Logger logger = LoggerFactory.getLogger(HtmlSchoolServiceImpl.class);

    private static final String school_list_href = "http://www.dzkbw.com";

    private static final int PAGE_SIZE = 10;
    private static final int AREA_LENGTH_2 = 2;
    private static final int AREA_LENGTH_3 = 3;
    private static final int AREA_LENGTH_5 = 5;
    private static final int AREA_LENGTH_7 = 7;

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private DownLoadImageService downLoadImageService;
    @Resource
    private GradeService gradeService;
    @Resource
    private VersionTypeService versionTypeService;
    @Resource
    private BookInfoService bookBookService;

    private static final String ROOT_PATH = "E:/images";

    @Override
    public String synAccountIncome(int d) {
        logger.info("执行第 {} ", d);
        String url = "http://fms.ddky.com/synAccount/outcome.htm?d=" + d;
        return HttpUtil2.doGetObj(url, null, true);
    }

    @Override
    public void synAccountOutcome(int d) {

    }

    @Override
    public List<? extends BookInfo> listSubjectInfo(String verType, String subType) {
        String url = school_list_href + "/books/" + verType + "/" + subType;
        Document document = getContent(url);
        Elements typeContent = document.getElementById("main").getElementsByClass("list mb_list");
        for (Element typeCont : typeContent) {
            List<BookInfo> chineseBookList = fromElement(typeCont);
            bookBookService.batchInsert(chineseBookList);
        }
        return null;
    }

    private List<BookInfo> fromElement(Element element) {
        List<GradeInfo> gradeInfos = gradeService.list();
        List<VersionType> versionTypes =  versionTypeService.list();
        Map<String, GradeInfo> gradeInfoMap = gradeInfos.stream().collect(Collectors.toMap(GradeInfo::getName, Function.identity()));
        Map<String, VersionType> versionTypeMap = versionTypes.stream().collect(Collectors.toMap(VersionType::getName, Function.identity()));
        List<Element> elLis = element.children().first().children();
        List<BookInfo> chineseBookList = new ArrayList<>();
        for (Element typeCont : elLis) {
            Element elImg = typeCont.getElementsByTag("img").first();
            String imageUrl = school_list_href + elImg.attr("data-original");
            String imageSavePath = ROOT_PATH + elImg.attr("data-original");
            String name = elImg.attr("alt");
            downLoadImageService.download(imageUrl, imageSavePath);
            logger.info(typeCont.toString());
            BookInfo chineseBook = new BookInfo();
            chineseBook.setBookName(name);
            //设置年级
            gradeInfoMap.forEach((key, value) -> {
                if (name.contains(key)) {
                    chineseBook.setGradeId(value.getGradeId());
                }
            });
            //设置版本
            versionTypeMap.forEach((key, value) -> {
                if (chineseBook.getBookName().contains(key)) {
                    chineseBook.setPublisher(value.getVerId());
                }
            });
            //设置卷册
            for (VolumeEnum item : VolumeEnum.values()) {
                if (chineseBook.getBookName().contains(item.getTitle())) {
                    chineseBook.setVolume(item.getCode());
                }
            }
            //选修和必修
            for (ElectiveEnum item : ElectiveEnum.values()) {
                if (chineseBook.getBookName().contains(item.getTitle())) {
                    chineseBook.setElective(item.getCode());
                }
            }
            if (chineseBook.getVolume() == null) {
                chineseBook.setVolume(0);
            }
            if (chineseBook.getElective() == null) {
                chineseBook.setElective(1);
            }
            chineseBook.setShortName(chineseBook.getBookName());
            chineseBook.setTheYear(2020);
            chineseBook.setCoverImage(elImg.attr("data-original"));
            logger.info(JSON.toJSONString(chineseBook));
            chineseBookList.add(chineseBook);
        }
        return chineseBookList;
    }
}
