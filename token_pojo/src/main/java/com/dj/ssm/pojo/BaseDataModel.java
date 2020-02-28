package com.dj.ssm.pojo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseDataModel {
	private Integer id;
	private Integer createUserId;
	private Date createTime;
	private Integer updateUserId;
	private Date  updateTime;
	private Integer isDel;

	
}
