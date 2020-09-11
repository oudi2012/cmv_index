package com.ddky.fms.refund.service.impl.exam;


import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.AskQuestionMapper;
import com.ddky.fms.refund.model.exam.entry.AskQuestion;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.AskQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * AskQuestionService
 * @author a
 */
@Service
public class AskQuestionServiceImpl extends AbstractExamService<AskQuestion> implements AskQuestionService {

    @Autowired
    private AskQuestionMapper askQuestionMapper;

    @Override
    public ExamInfoMapper<AskQuestion> getExamInfoMapper() {
        return askQuestionMapper;
    }
}
