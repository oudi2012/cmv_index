drop table if exists adm_user_info;
#id, userName, passWord, areaId, headImage, realName, role
CREATE TABLE adm_user_info (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  userName varchar(100) NOT NULL,
  passWord varchar(100) NOT NULL,
  areaId int(10) DEFAULT NULL comment '所属地区',
  headImage varchar(100) DEFAULT NULL comment '头像',
  realName varchar(100) DEFAULT NULL comment '真实姓名',
  role tinyint(1) DEFAULT 3 comment '角色',
  regDate int(10) DEFAULT UNIX_TIMESTAMP(),
  updateTime int(10) DEFAULT UNIX_TIMESTAMP(),
  PRIMARY KEY (id)
) ENGINE=innodb AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '管理员';

insert into adm_user_info(userName, passWord, areaId, headImage, realName, role)
values ('admin','uS2mNFX6yio=',101,'aaa','root',1);