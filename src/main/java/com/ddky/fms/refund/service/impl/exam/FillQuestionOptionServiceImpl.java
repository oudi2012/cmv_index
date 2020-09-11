package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.FillQuestionOptionMapper;
import com.ddky.fms.refund.model.exam.entry.FillQuestionOption;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.FillQuestionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * @author a
 */
@Service
public class FillQuestionOptionServiceImpl extends AbstractExamService<FillQuestionOption> implements FillQuestionOptionService {

    @Autowired
    private FillQuestionOptionMapper fillQuestionOptionMapper;

    @Override
    public ExamInfoMapper<FillQuestionOption> getExamInfoMapper() {
        return fillQuestionOptionMapper;
    }
}
