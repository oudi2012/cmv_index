package com.ddky.fms.refund.model.books.wyw.vo;

import lombok.Data;

/***
 * 短信模板
 * @author		SunXianJia
 * @version		1.0
 * Created		2016年8月24日 上午10:00:47
 */
@Data
public class SmsVo {
	
	private String[] mobiles;
	
	private String source;
	
	private String[] tempParameters;
	
	private Long templateId;
	 
	public SmsVo(String[] mobiles, Long templateId, String[] tempParameters){
		this.mobiles=mobiles;
		this.source="fms";
		this.templateId=templateId; 
		this.tempParameters = tempParameters;
	}
}
