package xyz.mxue.assistant.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author mxuexxmy
 * @date 4/14/2021$ 11:56 AM$
 */
@Controller
public class MyErrorController implements ErrorController {

    private final String prefix = "/pages/system";

    public MyErrorController() {}

    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {

            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == NOT_FOUND.value()) {
                return prefix + "/404";
            }
            else if(statusCode == INTERNAL_SERVER_ERROR.value()) {
                return prefix +"/500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
