package com.dj.ssm.commons.common;

public class SystemConstant {

	/**
	 * 1：未删除
	 */
	public static final Integer NOT_DELETE_IS_DEL = 1;

	/**
	 * 2：删除
	 */
	public static final Integer DELETE_IS_DEL = 2;

	/**
	 * 分页 3
	 */
	public static final Integer PAGE_SIZE = 3;

	/**
	 * 职位parentId 23
	 */
	public static final Integer LEVEL_PARENT_ID = 23;

	/**
	 * Id为24 查普通用户
	 */
	public static final Integer NORMAL_ID = 24;

	/**
	 * Id为25 查经理
	 */
	public static final Integer MANAGE_ID = 25;

	/**
	 * Id为26 查管理员
	 */
	public static final Integer ADMINISTRATOR_ID = 26;
	
	/**
	 * 收支项目 parentId 27
	 */
	public static final Integer PROJECT_PARENT_ID = 27;

	/**
	 * 待审批39
	 */
	public static final Integer WAIT_APPROVE = 39;

	/**
	 * 已拒绝40
	 */
	public static final Integer AFTER_NOT = 40;

	/**
	 * 待支付41
	 */
	public static final Integer WAIT_PLAY = 41;

	/**
	 * 已支付42
	 */
	public static final Integer AFTER_PLAY = 42;
	/**
	 * 提交报销单 47
	 */
	public static final Integer SUBMIT_EXP = 47;
	
	/**
	 * 审批通过 48
	 */
	public static final Integer APPROVE_YES = 48;
	
	/**
	 * 审批不通过 49
	 */
	public static final Integer APPROVE_NOT = 49;
	
	/**
	 * 支付成功 50
	 */
	public static final Integer PAY_SUCCESS = 50;

	/**
	 * 支付方式parentId 51
	 */
	public static final Integer PAY_PARENT_ID = 51;
	
	/**
	 * 输入不能为空
	 */
	public static final String NOT_NULL = "输入不能为空";

	/**
	 * 服务器异常
	 */
	public static final String EXCEPTION = "服务器异常";

	/**
	 * 账号或密码错误
	 */
	public static final String NUMBER_PWD_ERROR = "账号或密码错误";
	
	/**
	 * 该邮箱已注册
	 */
	public static final String EMAIL_ERROR = "该邮箱已注册";

	/**
	 * 该电话号已注册
	 */
	public static final String PHONE_ERROR = "该电话号已注册";

	/**
	 * 该用户名已注册
	 */
	public static final String NAME_ERROR = "该用户名已注册";
	
	/**
	 * 输入有误
	 */
	public static final String INPUT_ERROR = "输入有误";
	
	
	/**
	 * 该验证码以失效，请重新获取
	 */
	public static final String TIME_OUT = "该验证码以失效，请重新获取";
	

	/**
	 *查 状态parentId 38
	 */
	public static final Integer STATUS_PARENT_ID = 38;

}
