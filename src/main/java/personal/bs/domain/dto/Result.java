package personal.bs.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import personal.bs.domain.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    public Result(){
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
    }
    public Result(ResponseCode res){
        this.code = res.getCode();
        this.message = res.getMsg();
    }

    public Result(ResponseCode res, String msg){
        this.code = res.getCode();
        this.message = msg;
    }

    public Result(T data){
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private T data;

    public boolean SUCCESS(){
        if(this.code == 0){
            return true;
        }
        return false;
    }

    public boolean FAILED(){
        if(this.code == 0){
            return false;
        }
        return true;
    }
}
