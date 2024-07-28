package com.example.lams;

import com.example.lams.annotation.Authenticated;
import com.example.lams.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

@Component
public class EmployeeAuthInterceptor implements HandlerInterceptor {

    private final Logger log = LogManager.getLogger(getClass());
    private static final String AUTH_HEADER_PARAMETER_AUTHERIZATION = "TOKEN";

    @Autowired
    EmployeeService employeeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authenticated authenticated = method.getAnnotation(Authenticated.class);

        if (authenticated == null) {
            //No need to authenticate this method. Moving forward with interceptor chain.
            log.debug(
                    "@Authenticated not specified. Moving forward without authentication."
                            + request.getRequestURI());
            return true;
        }

        boolean isValidToken = false;
        log.info("[Inside PRE Handle interceptor][{}][{}]{}", request, request.getMethod(), request.getRequestURI());

        try {
            String basicAuthHeaderValue = request.getHeader(AUTH_HEADER_PARAMETER_AUTHERIZATION);
//            isValidToken = employeeService.validateBasicAuthentication(basicAuthHeaderValue);
            isValidToken = true;


            if (!isValidToken) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                throw new Exception("User is Unauthorized to perform the request");
            }
        } catch (Exception e) {
            log.error("Error occurred while authenticating request : {}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new Exception("Error occurred while authenticating request", e);
        }
        return isValidToken;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        log.info("[Inside POST Handle Interceptor]{}", request.getRequestURI());

    }
}
