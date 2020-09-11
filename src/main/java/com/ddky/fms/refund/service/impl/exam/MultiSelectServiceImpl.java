package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.MultiSelectMapper;
import com.ddky.fms.refund.model.books.entry.SubjectInfo;
import com.ddky.fms.refund.model.exam.entry.MultiSelectQuestion;
import com.ddky.fms.refund.model.exam.vo.MultiSelectVo;
import com.ddky.fms.refund.model.exam.vo.SelectQuestionVo;
import com.ddky.fms.refund.model.students.entry.GradeInfo;
import com.ddky.fms.refund.service.book.SubjectInfoService;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.MultiSelectService;
import com.ddky.fms.refund.service.exam.SelectQuestionService;
import com.ddky.fms.refund.service.student.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/****
 * MultiSelectService
 * @author a
 */
@Service("multiSelectService")
public class MultiSelectServiceImpl extends AbstractExamService<MultiSelectQuestion> implements MultiSelectService {

    @Resource
    private MultiSelectMapper multiSelectMapper;
    @Resource
    private SelectQuestionService selectQuestionService;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private GradeService gradeService;

    @Override
    public ExamInfoMapper<MultiSelectQuestion> getExamInfoMapper() {
        return multiSelectMapper;
    }

    @Override
    public MultiSelectVo itemToVo(MultiSelectQuestion multiSelectQuestion) {
        SubjectInfo subjectInfo = subjectInfoService.findById(multiSelectQuestion.getSubjectId());
        GradeInfo gradeInfo = gradeService.findById(multiSelectQuestion.getGradeId());
        MultiSelectVo multiSelectVo = new MultiSelectVo();
        BeanUtils.copyProperties(multiSelectQuestion, multiSelectVo);
        multiSelectVo.setGradeName(gradeInfo.getName());
        multiSelectVo.setSubjectName(subjectInfo.getTitle());
        if (!CollectionUtils.isEmpty(multiSelectQuestion.getSelectQuestionList())) {
            List<SelectQuestionVo> selectQuestionVoList = selectQuestionService.listToVo(multiSelectQuestion.getSelectQuestionList());
            if (!CollectionUtils.isEmpty(selectQuestionVoList)) {
                multiSelectVo.setSelectQuestionVoList(selectQuestionVoList);
            }
        }
        return multiSelectVo;
    }

    @Override
    public List<MultiSelectVo> listToVo(List<MultiSelectQuestion> multiSelectQuestionList) {
        if (CollectionUtils.isEmpty(multiSelectQuestionList)) {
            return null;
        }
        return multiSelectQuestionList.stream().map(this::itemToVo).collect(Collectors.toList());
    }
}
