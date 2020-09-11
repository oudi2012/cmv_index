package com.ddky.fms.refund.mapper.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.model.exam.entry.SelectQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * SelectQuestion
 * @author oudi
 */
public interface SelectQuestionMapper extends ExamInfoMapper<SelectQuestion> {

    /***
     * 根据问题组编号，删除选择项
     * @param multiIds multiIds
     * @return int
     */
    int deleteByMultiIds(@Param("idList") List<Long> multiIds);
}
