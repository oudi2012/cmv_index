package com.ddky.fms.refund.service.impl.student;

import com.alibaba.fastjson.JSON;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.mapper.students.SchoolInfoMapper;
import com.ddky.fms.refund.model.students.entry.AreaInfo;
import com.ddky.fms.refund.model.students.entry.SchoolInfo;
import com.ddky.fms.refund.model.students.vo.SchoolInfoVo;
import com.ddky.fms.refund.service.student.AreaInfoService;
import com.ddky.fms.refund.service.student.SchoolInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ddky.fms.refund.constants.CmvConstants.ONE_OO_OO;
import static com.ddky.fms.refund.constants.CmvConstants.ONE_OO_OO_OO;

/**
 * SchoolInfoService
 * @author wanghaikuo
 * @date 2020/7/3
 **/
@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {

    private static final Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);

    private static final String LOG_EDIT = "edit param {}";

    @Resource
    private SchoolInfoMapper schoolInfoMapper;
    @Resource
    private AreaInfoService areaInfoService;

    @Override
    public PageInfo<SchoolInfo> list(SchoolInfo schoolInfo, int pageIndex, int pageSize) {
        logger.info("PageInfo => list");
        PageHelper.startPage(pageIndex, pageSize).setOrderBy(" schoolId desc ");
        List<SchoolInfo> schoolInfoList = schoolInfoMapper.list(schoolInfo);
        if (CollectionUtils.isEmpty(schoolInfoList)) {
            return new PageInfo<>();
        }
        return new PageInfo<>(schoolInfoList);
    }

    @Override
    public List<SchoolInfo> listByParam(SchoolInfo schoolInfo) {
        return schoolInfoMapper.list(schoolInfo);
    }

    @Override
    public SchoolInfoVo itemToVo(SchoolInfo schoolInfo) {
        SchoolInfoVo schoolInfoVo = new SchoolInfoVo();
        BeanUtils.copyProperties(schoolInfo, schoolInfoVo);
        List<Integer> areaIdList = areaInfoService.cutAreaCode(schoolInfo.getAreaId());
        Map<Integer, AreaInfo> areaInfoMap = areaInfoService.pathListByCode(areaIdList);
        setVoAreaInfo(schoolInfoVo, areaInfoMap);
        return schoolInfoVo;
    }

    @Override
    public List<SchoolInfoVo> listToVo(List<SchoolInfo> schoolInfoList) {
        List<Integer> areaCodeList = schoolInfoList.stream().map(SchoolInfo::getAreaId).collect(Collectors.toList());
        Map<Integer, AreaInfo> areaInfoMap = areaInfoService.pathListByCode(areaCodeList);
        List<SchoolInfoVo> schoolInfoVoList = new ArrayList<>();
        schoolInfoList.forEach(schoolItem -> {
            SchoolInfoVo schoolInfoVo = new SchoolInfoVo();
            BeanUtils.copyProperties(schoolItem, schoolInfoVo);
            setVoAreaInfo(schoolInfoVo, areaInfoMap);
            schoolInfoVoList.add(schoolInfoVo);
        });
        return schoolInfoVoList;
    }

    private void setVoAreaInfo(SchoolInfoVo schoolInfoVo, Map<Integer, AreaInfo> areaInfoMap) {
        List<Integer> areaIdList = areaInfoService.cutAreaCode(schoolInfoVo.getAreaId());
        areaIdList.forEach(areaId -> {
            if (areaInfoMap.containsKey(areaId)) {
                if (areaId/ONE_OO_OO_OO > 0) {
                    schoolInfoVo.setTownCode(areaInfoMap.get(areaId).getAreaCode());
                    schoolInfoVo.setTownName(areaInfoMap.get(areaId).getAreaName());
                } else if (areaId/ONE_OO_OO > 0) {
                    schoolInfoVo.setCityCode(areaInfoMap.get(areaId).getAreaCode());
                    schoolInfoVo.setCityName(areaInfoMap.get(areaId).getAreaName());
                } else {
                    schoolInfoVo.setProvinceCode(areaInfoMap.get(areaId).getAreaCode());
                    schoolInfoVo.setProvinceName(areaInfoMap.get(areaId).getAreaName());
                }
            }
        });
    }

    @Override
    public SchoolInfo findById(Long id) {
        return schoolInfoMapper.findById(id);
    }

    @Override
    public List<SchoolInfo> findByIds(List<Long> ids) {
        return schoolInfoMapper.findByIds(ids);
    }

    @Override
    public Map<Long, SchoolInfo> findMapByIds(List<Long> ids) {
        return findByIds(ids).stream().collect(Collectors.toMap(SchoolInfo::getSchoolId, Function.identity()));
    }

    @Override
    public SchoolInfoVo findVoById(Long id) {
        SchoolInfo schoolInfo = schoolInfoMapper.findById(id);
        if (ObjectUtils.isEmpty(schoolInfo)) {
            return null;
        }
        return itemToVo(schoolInfo);
    }

    @Override
    public void insert(SchoolInfo item) {
        schoolInfoMapper.insert(item);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void update(SchoolInfo item) {
        logger.info(LOG_EDIT, JSON.toJSONString(item));
        schoolInfoMapper.update(item);
    }

    @Override
    public void batchInsert(List<SchoolInfo> list) {
        schoolInfoMapper.batchInsert(list);
    }

    @Override
    public int delete(List<Long> idList) {
        return schoolInfoMapper.delete(idList);
    }
}
