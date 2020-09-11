package com.ddky.fms.refund.service.exam;

import com.ddky.fms.refund.mapper.ExamInfoMapper;
import com.ddky.fms.refund.model.exam.entry.ExamBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;


/***
 * ExamService
 * @author a
 * @param <T>
 */
public abstract class AbstractExamService<T extends ExamBean> implements ExamService<T>{

    /***
     * 获取Mapper
     * @return ExamInfoMapper
     */
    public abstract ExamInfoMapper<T> getExamInfoMapper();

    @Override
    public List<T> list(T t) {
        return getExamInfoMapper().list(t);
    }

    @Override
    public T findById(int id) {
        return getExamInfoMapper().findById(id);
    }

    @Override
    public void insert(T item) {
        getExamInfoMapper().insert(item);
    }

    @Override
    public void batchInsert(List<T> list) {
        getExamInfoMapper().batchInsert(list);
    }

    @Override
    public void update(T item) {
        getExamInfoMapper().update(item);
    }

    @Override
    public PageInfo<T> pageList(T t, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<T> list = getExamInfoMapper().list(t);
        if (CollectionUtils.isEmpty(list)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(list);
    }

    @Override
    public int delete(List<Long> idList) {
        return getExamInfoMapper().delete(idList);
    }
}
