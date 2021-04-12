package xyz.mxue.assistant.commons.enumeration;

/**
 * @author mxuexxmy
 * @date 4/12/2021$ 2:54 PM$
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 获取异常的状态码
     *
     * @return 状态码
     * @author yubaoshan
     * @date 2020/7/9 14:28
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     *
     * @return 提示信息
     * @author yubaoshan
     * @date 2020/7/9 14:28
     */
    String getMessage();

}
