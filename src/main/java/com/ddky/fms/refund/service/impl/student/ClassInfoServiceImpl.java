package com.ddky.fms.refund.service.impl.student;

import com.ddky.fms.refund.mapper.students.ClassInfoMapper;
import com.ddky.fms.refund.model.students.entry.ClassInfo;
import com.ddky.fms.refund.service.student.ClassInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 * @author a
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Resource
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> list() {
        return classInfoMapper.list();
    }

    @Override
    public ClassInfo findById(int id) {
        return classInfoMapper.findById(id);
    }

    @Override
    public void insert(ClassInfo areaInfo) {
        classInfoMapper.insert(areaInfo);
    }

    @Override
    public void update(ClassInfo areaInfo) {
        classInfoMapper.update(areaInfo);
    }

    @Override
    public void batchInsert(List<ClassInfo> list) {
        classInfoMapper.batchInsert(list);
    }

    @Override
    public void delete(int id) {
        classInfoMapper.delete(id);
    }
}
