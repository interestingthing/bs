package personal.bs.domain.enums;

import lombok.Getter;

/**
 * @Author: chenjingang@gauzi.com  2019/5/26 10:38
 */
@Getter
public enum StoreStatus {

    WAIT_AUDIT(0,"待审核"),
    PASS_AUDIT(1,"已审核"),
    FAIL_AUDIT(2,"审核未通过"),
    CLOSURE(3,"关闭");



    private Integer code;
    private String msg;

    StoreStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    StoreStatus(){}

}
