package com.ddky.fms.refund.service.exam;

import com.ddky.fms.refund.model.exam.entry.MultiSelectQuestion;
import com.ddky.fms.refund.model.exam.vo.MultiSelectVo;
import java.util.List;

/***
 * SelectQuestion
 * @author oudi
 */
public interface MultiSelectService extends ExamService<MultiSelectQuestion> {

    /***
     * 格式化单条
     * @param multiSelectQuestion multiSelectQuestion
     * @return List
     */
    MultiSelectVo itemToVo(MultiSelectQuestion multiSelectQuestion);

    /***
     * 格式化列表
     * @param multiSelectQuestionList multiSelectQuestionList
     * @return List
     */
    List<MultiSelectVo> listToVo(List<MultiSelectQuestion> multiSelectQuestionList);

}
