package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperQuestionTypeMapper;
import com.ddky.fms.refund.model.exam.entry.PaperQuestionType;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperQuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * PageQuestionTypeService
 * @author a
 */
@Service
public class PaperQuestionTypeServiceImpl extends AbstractExamService<PaperQuestionType> implements PaperQuestionTypeService {

    @Autowired
    private PaperQuestionTypeMapper pageQuestionTypeMapper;

    @Override
    public ExamInfoMapper<PaperQuestionType> getExamInfoMapper() {
        return pageQuestionTypeMapper;
    }
}
