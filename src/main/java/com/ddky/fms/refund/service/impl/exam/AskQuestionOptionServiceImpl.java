package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.AskQuestionOptionMapper;
import com.ddky.fms.refund.model.exam.entry.AskQuestionOption;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.AskQuestionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * AskQuestionOptionService
 * @author a
 */
@Service
public class AskQuestionOptionServiceImpl extends AbstractExamService<AskQuestionOption> implements AskQuestionOptionService {

    @Autowired
    private AskQuestionOptionMapper askQuestionOptionMapper;

    @Override
    public ExamInfoMapper<AskQuestionOption> getExamInfoMapper() {
        return askQuestionOptionMapper;
    }
}
