package xyz.mxue.assistant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.mxue.assistant.commons.constant.ResultCode;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 12/23/2020$ 5:03 PM$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public static <T> Result<T> succeed(String message) {
        return succeedWith(ResultCode.SUCCESS.getCode(),message, null);
    }

    public static <T> Result<T> succeed(String message,T data) {
        return succeedWith(ResultCode.SUCCESS.getCode(), message,data);
    }

    public static <T> Result<T> succeed(T data) {
        return succeedWith(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),data);
    }

    public static <T> Result<T> failed(String message) {
        return failedWith(ResultCode.UNKNOWN_ERROR.getCode(), message,null);
    }

    public static <T> Result<T> succeedWith(String code, String message, T data) {
        return new Result<>(code, message,data);
    }

    public static <T> Result<T> failed(String message,T data) {
        return failedWith(ResultCode.UNKNOWN_ERROR.getCode(), message,data);
    }

    public static <T> Result<T> failed(T data) {
        return succeedWith(ResultCode.UNKNOWN_ERROR.getCode(), ResultCode.UNKNOWN_ERROR.getMessage(),data);
    }

    public static <T> Result<T> failedWith(String code, String message,T data) {
        return new Result<>(code, message,data);
    }

}
