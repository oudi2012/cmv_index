package com.ddky.fms.refund.service.impl.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.mapper.exam.SelectQuestionMapper;
import com.ddky.fms.refund.model.books.entry.SubjectInfo;
import com.ddky.fms.refund.model.exam.entry.SelectQuestion;
import com.ddky.fms.refund.model.exam.entry.SelectQuestionOption;
import com.ddky.fms.refund.model.exam.vo.SelectQuestionVo;
import com.ddky.fms.refund.model.students.entry.GradeInfo;
import com.ddky.fms.refund.service.book.SubjectInfoService;
import com.ddky.fms.refund.service.exam.AbstractExamService;
import com.ddky.fms.refund.service.exam.SelectQuestionOptionService;
import com.ddky.fms.refund.service.exam.SelectQuestionService;
import com.ddky.fms.refund.service.student.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * SelectQuestionService
 * @author a
 */
@Service
public class SelectQuestionServiceImpl extends AbstractExamService<SelectQuestion> implements SelectQuestionService {

    @Resource
    private SelectQuestionMapper selectQuestionMapper;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private GradeService gradeService;
    @Resource
    private SelectQuestionOptionService selectQuestionOptionService;

    @Override
    public ExamInfoMapper<SelectQuestion> getExamInfoMapper() {
        return selectQuestionMapper;
    }

    @Override
    public SelectQuestionVo itemToVo(SelectQuestion selectQuestion) {
        SubjectInfo subjectInfo = subjectInfoService.findById(selectQuestion.getSubjectId());
        GradeInfo gradeInfo = gradeService.findById(selectQuestion.getGradeId());
        List<SelectQuestionOption> selectQuestionOptionList = selectQuestionOptionService.listByQuestionId(selectQuestion.getId());
        SelectQuestionVo selectQuestionVo = new SelectQuestionVo();
        BeanUtils.copyProperties(selectQuestion, selectQuestionVo);
        selectQuestionVo.setSelectQuestionOptionList(selectQuestionOptionList);
        selectQuestionVo.setSubjectName(subjectInfo.getTitle());
        selectQuestionVo.setGradeName(gradeInfo.getName());
        return selectQuestionVo;
    }

    @Override
    public List<SelectQuestionVo> listToVo(List<SelectQuestion> selectQuestionList) {
        List<Integer> subjectIds = selectQuestionList.stream().map(SelectQuestion::getSubjectId).collect(Collectors.toList());
        List<Long> questionIds = selectQuestionList.stream().map(SelectQuestion::getId).collect(Collectors.toList());
        List<Integer> gradeIds = selectQuestionList.stream().map(SelectQuestion::getGradeId).collect(Collectors.toList());
        Map<Long, List<SelectQuestionOption>> groupOptions = selectQuestionOptionService.mapByQuestionIds(questionIds);
        Map<Integer, SubjectInfo> subjectInfoMap = new HashMap<>(24);
        if (!CollectionUtils.isEmpty(subjectIds)) {
            subjectInfoMap = subjectInfoService.findMapByIds(subjectIds);
        }
        Map<Integer, GradeInfo> gradeInfoMap = new HashMap<>(24);
        if (!CollectionUtils.isEmpty(gradeIds)) {
            gradeInfoMap = gradeService.findMapByIds(gradeIds);
        }
        List<SelectQuestionVo> selectQuestionVos = new ArrayList<>();
        //需要完全获取到值后才能使用
        Map<Integer, SubjectInfo> finalSubjectInfoMap = subjectInfoMap;
        Map<Integer, GradeInfo> finalGradeInfoMap = gradeInfoMap;
        selectQuestionList.forEach(selectQuestionItem -> {
            SelectQuestionVo selectQuestionVo = new SelectQuestionVo();
            BeanUtils.copyProperties(selectQuestionItem, selectQuestionVo);
            if (groupOptions.containsKey(selectQuestionItem.getId())) {
                selectQuestionVo.setSelectQuestionOptionList(groupOptions.get(selectQuestionItem.getId()));
            }
            if (finalSubjectInfoMap.containsKey(selectQuestionItem.getSubjectId())) {
                selectQuestionVo.setSubjectName(finalSubjectInfoMap.get(selectQuestionItem.getSubjectId()).getTitle());
            }
            if (finalGradeInfoMap.containsKey(selectQuestionItem.getGradeId())) {
                selectQuestionVo.setGradeName(finalGradeInfoMap.get(selectQuestionItem.getGradeId()).getName());
            }
            selectQuestionVos.add(selectQuestionVo);
        });
        return selectQuestionVos;
    }

    @Override
    public int deleteByMultiIds(List<Long> multiIds) {
        return selectQuestionMapper.deleteByMultiIds(multiIds);
    }
}
