package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.SelectQuestionOptionMapper;
import com.ddky.fms.refund.model.exam.entry.SelectQuestionOption;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.SelectQuestionOptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * SelectQuestionOptionService
 * @author a
 */
@Service
public class SelectQuestionOptionServiceImpl extends AbstractExamService<SelectQuestionOption> implements SelectQuestionOptionService {

    @Resource
    private SelectQuestionOptionMapper questionOptionMapper;

    @Override
    public ExamInfoMapper<SelectQuestionOption> getExamInfoMapper() {
        return questionOptionMapper;
    }

    @Override
    public List<SelectQuestionOption> listByQuestionId(Long questionId) {
        return questionOptionMapper.listByQuestionId(questionId);
    }

    @Override
    public List<SelectQuestionOption> listByQuestionIds(List<Long> questionIdList) {
        return questionOptionMapper.listByQuestionIds(questionIdList);
    }

    @Override
    public Map<Long, List<SelectQuestionOption>> mapByQuestionIds(List<Long> questionIdList) {
        return questionOptionMapper.listByQuestionIds(questionIdList).stream().collect(Collectors.groupingBy(SelectQuestionOption::getQuestionId));
    }

    @Override
    public int deleteByQuestionId(Long questionId) {
        return questionOptionMapper.deleteByQuestionId(questionId);
    }
}
