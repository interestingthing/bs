package personal.bs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用于向页面传递信息的类
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Result implements Serializable {
    private boolean success;
    private int flag;
    private String message;


    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
