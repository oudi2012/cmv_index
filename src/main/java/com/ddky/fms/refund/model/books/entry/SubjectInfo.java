package com.ddky.fms.refund.model.books.entry;

import lombok.Data;

/***
 * 科目
 * @author a
 */
@Data
public class SubjectInfo {
    private Integer subjectId;
    private String name;
    private String title;
    private Integer orderNo;
}
