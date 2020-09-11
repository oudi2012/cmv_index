package com.ddky.fms.refund.model.term.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/***
 * 学期
 * @author a
 */
@Data
public class TermInfo {
    private Integer teamId;
    private Integer theYear;
    private String name;
    private Integer type;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
