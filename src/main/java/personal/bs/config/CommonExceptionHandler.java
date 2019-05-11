package personal.bs.config;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import personal.bs.domain.exception.AppException;
import personal.bs.domain.vo.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 */
@ControllerAdvice(annotations = {Controller.class})
@Slf4j
public class CommonExceptionHandler {


    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public Result appExceptionHandler(HttpServletRequest request, AppException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        if (ex != null) {
            if (StringUtils.isNotEmpty(ex.getMessage())) {
                if (ex.getLogError())
                    log.error("ERROR AppException:{}===>request_url={} {}{}，request_param={}，response_result={}",
                            ex.getMessage(), method, url, (param == null ? "" : "?" + param),
                            JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
                return new Result(false, ex.getMessage());
            }
            if (ex.getLogError())
                log.error("ERROR AppException:{}===>request_url={} {}{}，request_param={}，response_result={}",
                        ex.getMessage(), method, url, (param == null ? "" : "?" + param),
                        JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
            return new Result();
        }
        if (ex.getLogError())
            log.error("ERROR AppException:{}===>request_url={} {}{}，request_param={}，response_result={}", ex.getMessage(),
                    method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()),
                    JSON.toJSONString(new Result(false, ex.getMessage())));
        return new Result(false, ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public personal.bs.domain.vo.Result methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MethodArgumentNotValidException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR MethodArgumentNotValidException PrintStackTrace：", ex);

        BindingResult bindingResult = ex.getBindingResult();

        String errorMesssage = "参数异常:";

        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i != 0) {
                errorMesssage += ",";
            }
            errorMesssage += bindingResult.getFieldErrors().get(i).getDefaultMessage();
        }

        log.warn(errorMesssage);

        return new personal.bs.domain.vo.Result(false, errorMesssage);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result runTimeExcept(HttpServletRequest request, RuntimeException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR RuntimeException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR RuntimeException PrintStackTrace：", ex);
        return new Result(false, ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Result methodArgumentEx(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MethodArgumentTypeMismatchException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR MethodArgumentTypeMismatchException PrintStackTrace：", ex);

        String error = "参数" + ex.getName() + "非法!";
        log.warn(error);
        return new Result(false, error);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result methodMissArgumentEx(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MissingServletRequestParameterException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR MissingServletRequestParameterException PrintStackTrace：", ex);
        return new Result(false, ex.getMessage());
    }

    @ExceptionHandler(value = MySQLSyntaxErrorException.class)
    @ResponseBody
    public Result handlerMySQLSyntaxErrorException(MySQLSyntaxErrorException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MySQLSyntaxErrorException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR MySQLSyntaxErrorException PrintStackTrace：", ex);
        return new Result(false, ex.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result argsBindException(BindException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MissingServletRequestParameterException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR MissingServletRequestParameterException PrintStackTrace：", ex);

        BindingResult bindingResult = ex.getBindingResult();

        String errorMesssage = "参数";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getField();
        }
        errorMesssage += "非法!";
        log.warn(errorMesssage);
        return new Result(false, errorMesssage);
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Result argsMissException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        log.error("ERROR MissingServletRequestParameterException :{}===>request_url={} {}{}，request_param={}，response_result={}", ex, method, url, (param == null ? "" : "?" + param), JSON.toJSONString(request.getParameterMap()), JSON.toJSONString(new Result(false, ex.getMessage())));
        log.error("ERROR MissingServletRequestParameterException PrintStackTrace：", ex);

        return new Result(false, "请填写信息");
    }
}
