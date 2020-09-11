package com.ddky.fms.refund.model.term.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/***
 * 学期Teacher
 * @author a
 * id, teamId, schoolId, gradeId, classId, userId, bookId, bookCate, state, startTime, endTime, createTime
 */
@Data
public class TermTeachers {
    private Long id;
    private Integer teamId;
    private Integer schoolId;
    private Integer gradeId;
    private Integer classId;
    private Long userId;
    private Integer bookId;
    private Integer bookCate;
    private Integer state;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
