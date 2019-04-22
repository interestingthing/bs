package personal.bs.domain.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    SYS_ERROR(10001, "SYSTEM_ERROR"),
    BUSINESS_ERROR(10002, "SYSTEM_ERROR"),
    PARAM_ERROR(40006,"参数错误"),
    REMOTE_CLIENT_FAILED(10004, "调用远程服务失败"),
    REQUEST_DATA_OUTDATED(10005, "请求过期"),
    REQUEST_TOO_FREQUENT(10006,"请求过于频繁"),
    REQUEST_PROCESSING(10007,"请求处理中，请勿重复提交"),

    //GOODS
    GOODS_CHANGE_APPLY_SUCCESS(10104,"申请已成功"),
    GOODS_STOCK_LACK(10113, "库存不足"),
    GOODS_STOCK_CHECK_FAILED(10114, "库存校验失败"),

    ORDER_CREATE_FAILED(10200, "创建订单失败"),
    ORDER_CANCELED(10201, "订单已取消"),
    ORDER_NOT_EXIST(10202, "订单不存在"),


    QUERY_PARAM_NO_BLANK_ERROR(20001,"请求参数不能为空"),
    QUERY_ORDER_NO_EXISTS_ERROR(20003,"未查询到对应的订单记录"),

    //php接口定义的code
    PARAM_ERROR_CODE(40001, "参数错误"),
    API_RESULT_ERROR(40006, "Api异常"),
    USER_HAVE_NOT_LOGIN(40012,"未登录"),
    USER_NOT_AUTH_LOGIN(40013,"没有权限登录"),
    API_RESULT_UNKNOWN_ERROR(40014,"外部调用错误"),
    USER_NO_PERMISSION(40030,"没有权限操作"),
    USER_GET_ERROR(40031,"获取用户信息失败");

    public Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static boolean isSuccess(Integer code){
        if(code.equals(SUCCESS.code)){
            return true;
        }
        return false;
    }

    public static boolean isStatus(Integer code){
        if(code.equals(SUCCESS.code)){
            return true;
        }
        return false;
    }
}
