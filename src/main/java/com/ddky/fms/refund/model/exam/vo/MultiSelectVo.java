package com.ddky.fms.refund.model.exam.vo;

import com.ddky.fms.refund.model.exam.entry.MultiSelectQuestion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/***
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultiSelectVo extends MultiSelectQuestion {
    private String subjectName;
    private String gradeName;
    private List<SelectQuestionVo> selectQuestionVoList;
}
