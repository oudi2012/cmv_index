package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.QuestionTypeMapper;
import com.ddky.fms.refund.model.exam.entry.QuestionType;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * QuestionTypeService
 * @author a
 */
@Service
public class QuestionTypeServiceImpl extends AbstractExamService<QuestionType> implements QuestionTypeService {

    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    @Override
    public ExamInfoMapper<QuestionType> getExamInfoMapper() {
        return questionTypeMapper;
    }
}
