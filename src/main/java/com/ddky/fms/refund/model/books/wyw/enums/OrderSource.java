/**
 * 
 */
package com.ddky.fms.refund.model.books.wyw.enums;

/**
 * 订单来源枚举
 * @author zhumingzhou
 * @date 2014年12月17日
 */
public enum OrderSource {
	/** APP */
	APP_CLIENT(1,"APP",true, true, true), 
	WEBSITE(2,"H5网站", true, true, true),
	TELEPHONE(3 ,"电话 ", false, false, false),
	PICTURE_TAKED(4, "拍照", false, false, false),
	DELIVERY(5,"配送", true, true, true),
	MEITUAN(6,"美团", false, true, false),
	B2C(7, "B2C", false,  true, false),
	DIANPING(8, "大众点评", false, true, false),
	BAIDU(9, " 百度外卖",false, true, false),
	LIFEC(10, "生活圈",false, true, false),
	DAOWEI(11, "到位",false, true, false),
	ZHONGPU(12, "忠仆",false, true, false),
	B2CIMPORT(13, "B2C导入",false, true, false),
	SEXTOY(14, "情趣用品",false, false, false),
	PRESALE(15, "预售", false, false, false),
	PC(16, "PC", false, true, false),
	ELE(17, " ele外卖",false, true, false),
	TELEPHONE_B2C(18, "电话b2c", false, false, false),
	CHUNYU(19, "春雨医生", true, true, true),
	GAODE(20, "高德", true, true, true),
	BAOSHUIQU(21, "保税区", false, false, false),
	DAOJIA58(22, "58到家", true, true, true),
	MITU(23, "MITU", true, true, true),
	ZWYL(24, "自我药疗", true, true, true),
	PING_AN(25, "平安好医生", false, true, false),
	PC_YISHOU(26, "颐寿PC", false, true, false),
	ZHIYOU(27, "海外直邮", false, false, false);
	
	private int code;
	private String title;
	/** 风险审核 */
	private boolean riskyAudit;
	/** 标记审核 */
	private boolean flagAudit;
	/** 营销支持 */
	private boolean marketingSupport;
	
	OrderSource(int code, String title, boolean riskyAudit, boolean flagAudit, boolean marketingSupport) {
		this.code = code;
		this.title = title;
		this.riskyAudit = riskyAudit;
		this.flagAudit = flagAudit;
		this.marketingSupport = marketingSupport;
	}

	public static OrderSource get(int code) {
		for(OrderSource source : OrderSource.values()) {
			if(code == source.getCode()) {
				return source;
			}
		}
		return null;
	}
	
	public int getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
	
	public boolean isRiskyAudit() {
		return riskyAudit;
	}

	public boolean isFlagAudit() {
		return flagAudit;
	}
	
	public boolean isMarketingSupport(){
		return marketingSupport;
	}
}
