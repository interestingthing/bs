package personal.bs.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * 日志记录器
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    @Pointcut("execution(* personal.bs.controller.*Controller.*(..))")
    public void excudeService() {
        // Do nothing .
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();
        String str = "";
//        String wholeStr = "";
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        while((str = reader.readLine()) != null){
//            //一行一行的读取body体里面的内容；
//            wholeStr += str;
//        }
//
//        log.info("wholeStr = {}",wholeStr);
//        String headers="\n";
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            headers+=headerName+" = "+request.getHeader(headerName)+"\n";
//        }
//        log.info("headers = {}",headers);
        Object[] args = pjp.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        String paramter = "";
        if (arguments != null) {
            try {
                paramter = JSONObject.toJSONString(arguments);
            } catch (Exception e) {
                paramter = arguments.toString();
            }
        }

        //开始时间
        long startTime = System.currentTimeMillis();
        String startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        log.info("START===>request_url= {} {}{},request_method={}，request_param={}，begin_time={}",
                method, url, (param == null ? "" : "?" + param), ("{" + pjp.getTarget().getClass().getSimpleName() + "#"
                        + pjp.getSignature().getName() + "}"), JSON.toJSONString(paramter), startTimeStr);
        //执行代码
        Object result = pjp.proceed();
        //结束时间
        long endTime = System.currentTimeMillis();
        String endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        //总共用时
        long cousumeTime = endTime - startTime;


        log.info("END===>request_url= {} {}{}，request_method={}，consume_time={}ms，request_param={},response_result={}，" +
                        "begin_time={}，end_time={}", method, url, (param == null ? "" : "?" + param), ("{" +
                        pjp.getTarget().getClass().getSimpleName() + "#" + pjp.getSignature().getName() + "}"), cousumeTime,
                JSON.toJSONString(paramter), JSON.toJSONString(result),
                startTimeStr, endTimeStr);
        return result;
    }

}
