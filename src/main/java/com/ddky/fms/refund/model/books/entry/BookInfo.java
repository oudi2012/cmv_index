package com.ddky.fms.refund.model.books.entry;

import lombok.Data;

/***
 * 书籍基类
 * @author oudi
 *
 */
@Data
public class BookInfo implements java.io.Serializable{
	private Long bookId;
	/** 书名 */
	private  String bookName;
	/** 简称 */
	private  String shortName;
	/** 拼音 */
	private  String pinYin;
	/** 出版社 */
	private  Integer  publisher;
	/** 版本 */
	private  Integer  version;
	/** 年份 */
	private  int    theYear;
	/** 封面 */
	private  String coverImage;
	/** cip图片 */
	private  String cipImage;
	/** 卷册 1 上册 2 下册 */
	private Integer  volume;
	/** 选修0和必修1 */
	private Integer  elective;
	/** 科目 */
	private Integer  subjectId;
	/** 年级 */
	private Integer  gradeId;
	/** 创建时间 */
	private Long     createDate;
}
