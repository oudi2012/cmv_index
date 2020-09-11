package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperQuestionMapper;
import com.ddky.fms.refund.model.exam.entry.PaperQuestion;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * PageQuestionService
 * @author a
 */
@Service
public class PaperQuestionServiceImpl extends AbstractExamService<PaperQuestion> implements PaperQuestionService {

    @Autowired
    private PaperQuestionMapper pageQuestionMapper;

    @Override
    public ExamInfoMapper<PaperQuestion> getExamInfoMapper() {
        return pageQuestionMapper;
    }
}
