package com.ddky.fms.refund.model.term.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/***
 * 学期图书
 * @author a
 * id, schoolId, teamId, gradeId, name, bookId, bookCate, createTime
 */
@Data
public class TermBooks {
    private Long id;
    private Integer schoolId;
    private Integer teamId;
    private Integer gradeId;
    private String name;
    private Integer bookId;
    private Integer bookCate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
