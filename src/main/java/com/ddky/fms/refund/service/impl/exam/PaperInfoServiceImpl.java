package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperInfoMapper;
import com.ddky.fms.refund.model.exam.entry.PaperInfo;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/***
 * @author a
 */
@Service
public class PaperInfoServiceImpl extends AbstractExamService<PaperInfo> implements PaperInfoService {

    @Resource
    private PaperInfoMapper paperInfoMapper;

    @Override
    public ExamInfoMapper<PaperInfo> getExamInfoMapper() {
        return paperInfoMapper;
    }
}
