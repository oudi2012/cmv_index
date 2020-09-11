package com.ddky.fms.refund.model.exam.vo;

import com.ddky.fms.refund.model.exam.entry.SelectQuestion;
import com.ddky.fms.refund.model.exam.entry.SelectQuestionOption;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/***
 * @author a
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SelectQuestionVo extends SelectQuestion {
    private String subjectName;
    private String gradeName;
    private List<SelectQuestionOption> selectQuestionOptionList;
}
