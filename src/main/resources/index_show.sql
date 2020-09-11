drop table if exists v_menu_info;
#id, name, title, type, isShow, url, iconSrc, orderNo, creator, createTime,
CREATE TABLE v_menu_info (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  title varchar(100) NOT NULL,
  type int(2) DEFAULT 1 comment '类型：默认1,2',
  isShow tinyint(1) DEFAULT 1 comment '是否显示 0否 1是',
  url varchar(200) DEFAULT NULL comment '连接地址',
  iconSrc varchar(200) DEFAULT NULL comment '图标',
  regDate int(10) DEFAULT UNIX_TIMESTAMP(),
  updateTime int(10) DEFAULT UNIX_TIMESTAMP(),
  PRIMARY KEY (id)
) ENGINE=innodb AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '管理员';

insert into adm_user_info(userName, passWord, areaId, headImage, realName, role)
values ('admin','uS2mNFX6yio=',101,'aaa','root',1);