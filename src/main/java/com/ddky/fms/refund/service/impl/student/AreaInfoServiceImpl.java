package com.ddky.fms.refund.service.impl.student;

import com.ddky.fms.refund.mapper.students.AreaInfoMapper;
import com.ddky.fms.refund.model.students.entry.AreaInfo;
import com.ddky.fms.refund.service.student.AreaInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author a
 */
@Service
public class AreaInfoServiceImpl implements AreaInfoService {

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    public List<AreaInfo> listProvince() {
        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setParentCode(0);
        return areaInfoMapper.list(areaInfo);
    }

    @Override
    public List<AreaInfo> listCities(Integer provinceId) {
        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setParentCode(provinceId);
        return areaInfoMapper.list(areaInfo);
    }

    @Override
    public List<AreaInfo> listArea(Integer parentId) {
        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setParentCode(parentId);
        return areaInfoMapper.list(areaInfo);
    }

    @Override
    public List<AreaInfo> listTown(Integer cityId) {
        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setParentCode(cityId);
        return areaInfoMapper.list(areaInfo);
    }

    @Override
    public PageInfo<AreaInfo> list(AreaInfo areaInfo) {
        PageHelper.startPage(areaInfo.getPageIndex(), areaInfo.getPageSize()).setOrderBy(" areaCode asc");
        List<AreaInfo> areaInfoList = areaInfoMapper.list(areaInfo);
        if (CollectionUtils.isEmpty(areaInfoList)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(areaInfoList);
    }

    @Override
    public AreaInfo findByCode(int areaCode) {
        return areaInfoMapper.findByCode(areaCode);
    }

    @Override
    public void insert(AreaInfo areaInfo) {
        areaInfoMapper.insert(areaInfo);
    }

    @Override
    public AreaInfo editByCode(AreaInfo areaInfo) {
        areaInfoMapper.update(areaInfo);
        return areaInfo;
    }

    @Override
    public List<AreaInfo> pathListByCode(Integer areaCode) {
        List<Integer> areaCodeList = cutAreaCode(areaCode);
        return areaInfoMapper.findByCodeList(areaCodeList);
    }

    @Override
    public Map<Integer, AreaInfo> pathListByCode(List<Integer> areaCodeList) {
        Set<Integer> codeSet = new HashSet<>();
        areaCodeList.forEach(areaCode -> {
            List<Integer> subAreaCodeList = cutAreaCode(areaCode);
            codeSet.addAll(subAreaCodeList);
        });
        List<AreaInfo> areaInfoList = areaInfoMapper.findByCodeList(new ArrayList<>(codeSet));
        return areaInfoList.stream().collect(Collectors.toMap(AreaInfo::getAreaCode, Function.identity()));
    }

    @Override
    public Integer findMaxNodeByParentCode(int parentCode) {
        return areaInfoMapper.findMaxNodeByParentCode(parentCode);
    }

    @Override
    public void removeByCode(int areaCode) {
        areaInfoMapper.delete(areaCode);
    }

    @Override
    public void batchInsert(List<AreaInfo> list) {
        areaInfoMapper.batchInsert(list);
    }

    @Override
    public List<AreaInfo> listByNames(String provinceName, String cityName, String townName) {
        return areaInfoMapper.listByNames(provinceName, cityName, townName);
    }
}
