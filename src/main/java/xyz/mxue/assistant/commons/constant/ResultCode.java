package xyz.mxue.assistant.commons.constant;

import lombok.Getter;

/**
 * @author mxuexxmy
 * @date 3/29/2021$ 5:43 PM$
 */
@Getter
public enum ResultCode {
    // 未查询到相关数据
    NO_FOUND_DATA("N0000", "未查询到相关数据"),
    // 验证码已发送
    SUCCESS_VERIFICATIONCODE("K0002", "验证码已发送"),
    // 该用户验证码还在时效内
    ERROR_VERIFICATIONCODE_K0003("K0003", "该用户验证码还在时效内"),
    // 成功
    SUCCESS("0", "成功"),
    // 未知错误
    UNKNOWN_ERROR("-1", "未知错误"),
    // 操作成功
    OPERATION_SUCCESS_D0001("D0001", "操作成功"),
    // 操作失败
    OPERATION_FAIL_D0002("D0002", "操作失败"),
    // 用户注册错误
    USER_ERROR_A0100("A0100", "用户注册错误"),
    // 用户未同意隐私协议
    USER_ERROR_A0101("A0101", "用户未同意隐私协议"),
    // 用户名校验失败
    USER_ERROR_A0110("A0110", "用户名校验失败"),
    // 用户名已存在
    USER_ERROR_A0111("A0111", "用户名已存在"),
    // 密码校验失败
    USER_ERROR_A0120("A0120", "密码校验失败"),
    // 密码长度不够
    USER_ERROR_A0121("A0121", "密码长度不够"),
    // 密码强度不够
    USER_ERROR_A0122("A0122", "密码强度不够"),
    // 校验码输入错误
    USER_ERROR_A0130("A0130", "校验码输入错误"),
    // 短信校验码输入错误
    USER_ERROR_A0131("A0131", "短信校验码输入错误"),
    // 邮件校验码输入错误
    USER_ERROR_A0132("A0132", "邮件校验码输入错误"),
    // 用户基本信息校验失败
    USER_ERROR_A0150("A0150", "用户基本信息校验失败"),
    // 手机格式校验失败
    USER_ERROR_A0151("A0151", "手机格式校验失败"),
    // 地址格式校验失败
    USER_ERROR_A0152("A0152", "地址格式校验失败"),
    // 邮箱格式校验失败
    USER_ERROR_A0153("A0153", "邮箱格式校验失败"),
    // 用户登录异常
    USER_ERROR_A0200("A0200", "用户登录异常"),
    // 用户账户不存在
    USER_ERROR_A0201("A0201", "用户账户不存在"),
    // 用户账户被冻结
    USER_ERROR_A0202("A0202", "用户账户被冻结"),
    // 用户账户已作废
    USER_ERROR_A0203("A0203", "用户账户已作废"),
    // 用户密码错误
    USER_ERROR_A0210("A0210", "用户密码错误"),
    // 用户登录已过期
    USER_ERROR_A0230("A0230", "用户登录已过期"),
    // 用户验证码错误
    USER_ERROR_A0240("A0240", "用户验证码错误"),
    // 用户验证码尝试次数超限
    USER_ERROR_A0241("A0241", "用户验证码尝试次数超限"),
    // 访问权限异常
    USER_ERROR_A0300("A0300", "访问权限异常"),
    // 访问未授权
    USER_ERROR_A0301("A0301", "访问未授权"),
    // 正在授权中
    USER_ERROR_A0302("A0302", "正在授权中"),
    // 用户授权申请被拒绝
    USER_ERROR_A0303("A0303", "用户授权申请被拒绝"),
    USER_ERROR_A0402("A0402", "无效的用户输入"),
    // 用户上传文件异常
    USER_ERROR_A0700("A0700", "用户上传文件异常"),
    // 用户上传文件类型不匹配
    USER_ERROR_A0701("A0701", "用户上传文件类型不匹配"),
    // 用户上传文件太大
    USER_ERROR_A0702("A0702", "用户上传文件太大"),
    // 用户上传图片太大
    USER_ERROR_A0703("A0703", "用户上传图片太大"),
    // 用户上传视频太大
    USER_ERROR_A0704("A0704", "用户上传视频太大"),
    // 用户上传压缩文件太大
    USER_ERROR_A0705("A0705", "用户上传压缩文件太大"),
    // 数据库服务超时
    SERVICE_ERROR_C0250("C0250", "数据库服务超时"),
    // 数据库服务出错
    SERVICE_ERROR_C0300("C0300", "数据库服务出错");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
