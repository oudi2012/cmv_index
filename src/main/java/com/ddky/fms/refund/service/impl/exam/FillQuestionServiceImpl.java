package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.FillQuestionMapper;
import com.ddky.fms.refund.model.exam.entry.FillQuestion;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.FillQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * FillQuestionService
 * @author a
 */
@Service
public class FillQuestionServiceImpl extends AbstractExamService<FillQuestion> implements FillQuestionService {

    @Autowired
    private FillQuestionMapper fillQuestionMapper;

    @Override
    public ExamInfoMapper<FillQuestion> getExamInfoMapper() {
        return fillQuestionMapper;
    }
}
