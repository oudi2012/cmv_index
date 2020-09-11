package com.ddky.fms.refund.service.html;

import com.ddky.fms.refund.model.students.entry.SchoolInfo;

import java.util.List;

/***
 * 学校数据
 * @author a
 */
public interface HtmlSchoolService {

    /***
     * 根据xpath 获取学校信息
     * @param pageIndex
     * @return
     */
    List<SchoolInfo> listSchoolInfo(int pageIndex);

}
