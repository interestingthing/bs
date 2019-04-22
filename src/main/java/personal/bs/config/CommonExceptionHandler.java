package personal.bs.config;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import personal.bs.domain.dto.Result;
import personal.bs.domain.enums.ResponseCode;
import personal.bs.domain.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = {Controller.class})
@Slf4j
public class CommonExceptionHandler {


    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public Result appExceptionHandler(HttpServletRequest request, AppException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        if (ex.getResponseCode() != null) {
            if (StringUtils.isNotEmpty(ex.getResponseCode().getMsg())) {
                if (ex.getLogError())
                    log.error("ERROR AppException:{}===>request_url={} {}{}，request_param={}，response_result={}", ex.getMessage(), method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ex.getResponseCode(), ex.getMessage())));
                return new Result(ex.getResponseCode(), ex.getMessage());
            }
            if (ex.getLogError())
                log.error("ERROR AppException:{}===>request_url={} {}{}，request_param={}，response_result={}", ex.getMessage(), method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ex.getResponseCode())));
            return new Result(ex.getResponseCode());
        }
        if (ex.getLogError())
            log.error("ERROR AppException:{}===>request_url={} {}{}，request_param={}，response_result={}", ex.getMessage(), method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.SYS_ERROR, ex.getMessage())));
        return new Result<>(ResponseCode.SYS_ERROR, ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MethodArgumentNotValidException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.PARAM_ERROR, ex.getMessage())));
        log.error("ERROR MethodArgumentNotValidException PrintStackTrace：", ex);

        BindingResult bindingResult = ex.getBindingResult();

        String errorMesssage = "参数异常:";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }


        return new Result<>(ResponseCode.PARAM_ERROR, errorMesssage);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result runTimeExcept(HttpServletRequest request, RuntimeException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR RuntimeException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.SYS_ERROR, ex.getMessage())));
        log.error("ERROR RuntimeException PrintStackTrace：", ex);
        return new Result<>(ResponseCode.PARAM_ERROR, ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Result methodArgumentEx(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MethodArgumentTypeMismatchException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.SYS_ERROR, ex.getMessage())));
        log.error("ERROR MethodArgumentTypeMismatchException PrintStackTrace：", ex);

        String error = "参数" + ex.getName() + "非法!";
        return new Result<>(ResponseCode.PARAM_ERROR, error);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result methodMissArgumentEx(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MissingServletRequestParameterException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.PARAM_ERROR, ex.getMessage())));
        log.error("ERROR MissingServletRequestParameterException PrintStackTrace：", ex);
        return new Result<>(ResponseCode.PARAM_ERROR, ex.getMessage());
    }

    @ExceptionHandler(value = MySQLSyntaxErrorException.class)
    @ResponseBody
    public Result handlerMySQLSyntaxErrorException(MySQLSyntaxErrorException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MySQLSyntaxErrorException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.PARAM_ERROR, ex.getMessage())));
        log.error("ERROR MySQLSyntaxErrorException PrintStackTrace：", ex);
        return new Result<>(ResponseCode.PARAM_ERROR, ex.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result argsBindException(BindException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MissingServletRequestParameterException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(ResponseCode.PARAM_ERROR, ex.getMessage())));
        log.error("ERROR MissingServletRequestParameterException PrintStackTrace：", ex);

        BindingResult bindingResult = ex.getBindingResult();

        String errorMesssage = "参数";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getField();
        }
        errorMesssage += "非法!";
        return new Result<>(ResponseCode.PARAM_ERROR, errorMesssage);
    }

}
