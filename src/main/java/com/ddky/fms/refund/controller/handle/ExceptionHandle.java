package com.ddky.fms.refund.controller.handle;

import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.model.ResponseObject;
import com.ddky.fms.refund.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * @author a
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseObject handle(Exception e){
        ResponseObject responseObject = new ResponseObject();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            CommonUtils.executeFailure(responseObject,  businessException.getMessage());
        }else {
            e.printStackTrace();
            CommonUtils.executeFailure(responseObject,  "未知错误");
        }
        return responseObject;

    }
}
