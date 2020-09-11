package com.ddky.fms.refund.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.ddky.fms.refund.exception.BusinessException;
import com.ddky.fms.refund.model.GeneralStatus;
import com.ddky.fms.refund.model.ResponseObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 *通用工具类
 * @author a
 */
public class CommonUtils {

	/**
     * 返回处理失败的信息
     */
    public static void executeFailure(ResponseObject resObj, String message) {
        resObj.setCode(GeneralStatus.failure.getStatus());
        if(StringUtils.isEmpty(message)){
			resObj.setMessage(GeneralStatus.failure.getDetail());
		}else{
			resObj.setMessage(message);
		}
		resObj.setData(new HashMap<String, Object>(0));
    }
	/**
	 * 返回处理成功的信息
	 */
	public static void executeSuccess(ResponseObject resObj) {
		executeSuccess(resObj, null);
	}
    /**
	 * 返回处理成功的信息
	 */
	public static void executeSuccess(ResponseObject resObj, Object o) {
		resObj.setCode(GeneralStatus.success.getStatus());
		resObj.setMessage(GeneralStatus.success.getDetail());
		resObj.setData(o);
	}
}