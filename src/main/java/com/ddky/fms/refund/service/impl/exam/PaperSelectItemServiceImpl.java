package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.PaperSelectItemMapper;
import com.ddky.fms.refund.model.exam.entry.PaperSelectItem;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.PaperSelectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * PageSelectItemService
 * @author a
 */
@Service
public class PaperSelectItemServiceImpl extends AbstractExamService<PaperSelectItem> implements PaperSelectItemService {

    @Autowired
    private PaperSelectItemMapper pageSelectItemMapper;

    @Override
    public ExamInfoMapper<PaperSelectItem> getExamInfoMapper() {
        return pageSelectItemMapper;
    }
}
