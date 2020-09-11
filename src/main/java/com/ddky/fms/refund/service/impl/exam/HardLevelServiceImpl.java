package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.HardLevelMapper;
import com.ddky.fms.refund.model.exam.entry.HardLevel;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.HardLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * HardLevelService
 * @author a
 */
@Service
public class HardLevelServiceImpl extends AbstractExamService<HardLevel> implements HardLevelService {

    @Autowired
    private HardLevelMapper hardLevelMapper;

    @Override
    public ExamInfoMapper<HardLevel> getExamInfoMapper() {
        return hardLevelMapper;
    }
}
