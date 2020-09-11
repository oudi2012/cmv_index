package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperSelectMapper;
import com.ddky.fms.refund.model.exam.entry.PaperSelect;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * PageSelectService
 * @author a
 */
@Service
public class PaperSelectServiceImpl extends AbstractExamService<PaperSelect> implements PaperSelectService {

    @Autowired
    private PaperSelectMapper pageSelectMapper;

    @Override
    public ExamInfoMapper<PaperSelect> getExamInfoMapper() {
        return pageSelectMapper;
    }
}
