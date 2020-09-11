package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperResultInfoMapper;
import com.ddky.fms.refund.model.exam.entry.PaperResultInfo;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperResultInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * PageResultInfoService
 * @author a
 */
@Service
public class PaperResultInfoServiceImpl extends AbstractExamService<PaperResultInfo> implements PaperResultInfoService {

    @Autowired
    private PaperResultInfoMapper pageResultInfoMapper;

    @Override
    public ExamInfoMapper<PaperResultInfo> getExamInfoMapper() {
        return pageResultInfoMapper;
    }
}
