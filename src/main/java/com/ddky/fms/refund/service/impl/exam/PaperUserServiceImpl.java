package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperUserMapper;
import com.ddky.fms.refund.model.exam.entry.PaperUser;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * PageUserService
 * @author a
 */
@Service
public class PaperUserServiceImpl extends AbstractExamService<PaperUser> implements PaperUserService {

    @Autowired
    private PaperUserMapper pageUserMapper;

    @Override
    public ExamInfoMapper<PaperUser> getExamInfoMapper() {
        return pageUserMapper;
    }
}
