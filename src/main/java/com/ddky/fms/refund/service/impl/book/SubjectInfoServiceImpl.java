package com.ddky.fms.refund.service.impl.book;

import com.ddky.fms.refund.mapper.books.SubjectInfoMapper;
import com.ddky.fms.refund.model.books.entry.SubjectInfo;
import com.ddky.fms.refund.service.book.SubjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author wanghaikuo
 * @Date 2020/7/6
 **/
@Service
public class SubjectInfoServiceImpl implements SubjectInfoService {

    @Resource
    private SubjectInfoMapper subjectInfoMapper;

    @Override
    public List<SubjectInfo> list() {
        return subjectInfoMapper.list();
    }

    @Override
    public SubjectInfo findById(int id) {
        return subjectInfoMapper.findById(id);
    }

    @Override
    public SubjectInfo findByName(String name) {
        return subjectInfoMapper.findByName(name);
    }

    @Override
    public List<SubjectInfo> findByIds(List<Integer> ids) {
        return subjectInfoMapper.findByIds(ids);
    }

    @Override
    public Map<Integer, SubjectInfo> findMapByIds(List<Integer> ids) {
        return subjectInfoMapper.findByIds(ids).stream().collect(Collectors.toMap(SubjectInfo::getSubjectId, Function.identity()));
    }

    @Override
    public void insert(SubjectInfo item) {
        subjectInfoMapper.insert(item);
    }

    @Override
    public void batchInsert(List<SubjectInfo> list) {
        subjectInfoMapper.batchInsert(list);
    }

    @Override
    public void update(SubjectInfo item) {
        subjectInfoMapper.update(item);
    }

    @Override
    public int delete(List<Long> idList) {
        return subjectInfoMapper.delete(idList);
    }
}
