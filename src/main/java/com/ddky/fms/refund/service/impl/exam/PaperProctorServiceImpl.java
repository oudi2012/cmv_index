package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperProctorMapper;
import com.ddky.fms.refund.model.exam.entry.PaperProctor;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperProctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * PageProctorService
 * @author a
 */
@Service
public class PaperProctorServiceImpl extends AbstractExamService<PaperProctor> implements PaperProctorService {

    @Autowired
    private PaperProctorMapper pageProctorMapper;

    @Override
    public ExamInfoMapper<PaperProctor> getExamInfoMapper() {
        return pageProctorMapper;
    }
}
