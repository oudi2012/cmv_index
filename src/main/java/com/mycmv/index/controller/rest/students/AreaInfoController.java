package com.mycmv.index.controller.rest.students;

import com.mycmv.server.model.ResponseObject;
import com.mycmv.server.model.students.entry.AreaInfo;
import com.mycmv.server.service.student.AreaInfoService;
import com.mycmv.server.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * 区域管理
 * @author a
 */
@RestController
@RequestMapping("area")
public class AreaInfoController {

    @Resource
    private AreaInfoService areaInfoService;

    @ResponseBody
    @GetMapping("provinces")
    public ResponseObject provinces() {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.listProvince());
        return resObj;
    }

    @ResponseBody
    @GetMapping("cities")
    public ResponseObject cities(int provinceId) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.listCities(provinceId));
        return resObj;
    }

    @ResponseBody
    @GetMapping("towns")
    public ResponseObject towns(int cityId) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.listTown(cityId));
        return resObj;
    }

    @ResponseBody
    @GetMapping("areaList")
    public ResponseObject areaList(int parentId) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.listArea(parentId));
        return resObj;
    }

    @ResponseBody
    @GetMapping("pathListByCode")
    public ResponseObject pathListByCode(int parentId) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.pathListByCode(parentId));
        return resObj;
    }

    @ResponseBody
    @PostMapping("findByCode")
    public ResponseObject findById(int code) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.findByCode(code));
        return resObj;
    }

    @ResponseBody
    @PostMapping("areaAdd")
    public ResponseObject areaAdd(@RequestBody AreaInfo areaInfo) {
        ResponseObject resObj = new ResponseObject();
        Integer maxAreaCode = areaInfoService.findMaxNodeByParentCode(areaInfo.getParentCode());
        if (maxAreaCode == null) {
            maxAreaCode = areaInfo.getParentCode() * 100;
        }
        areaInfo.setAreaCode(maxAreaCode + 1);
        areaInfoService.insert(areaInfo);
        CommonUtils.executeSuccess(resObj, areaInfo);
        return resObj;
    }

    @ResponseBody
    @PostMapping("areaEdit")
    public ResponseObject areaEdit(@RequestBody AreaInfo areaInfo) {
        ResponseObject resObj = new ResponseObject();
        CommonUtils.executeSuccess(resObj, areaInfoService.editByCode(areaInfo));
        return resObj;
    }

    @ResponseBody
    @GetMapping("areaDel")
    public ResponseObject areaDel(Integer areaCode) {
        ResponseObject resObj = new ResponseObject();
        areaInfoService.removeByCode(areaCode);
        CommonUtils.executeSuccess(resObj);
        return resObj;
    }
}
