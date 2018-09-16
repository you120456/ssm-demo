package com.ssm.common;

/**
 * @Author: 姜飞祥
 * @Description: 封装结果集成统一的json样式
 * @Date: Create in 2018/9/15 12:08
 * @param: $params$
 * @return: $returns$
 */
public class ResultData<T>{
    /**
     * 信息状态码，0表示成功，1表示失败
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回的对象,因为有分页信息，方便返回json数据见名之意，这里直接变量名为pageInfo。
     */
    private T pageInfo;

    /**
     * 成功时调用的函数
     * @param object 要返回的对象
     * @return
     */
    public static ResultData<Object> success(Object object) {
        ResultData result = new ResultData();
        result.setCode(200);
        result.setMsg("成功");
        result.setPageInfo(object);
        return result;
    }

    /**
     * 成功时，如果没有要返回的对象，调用此方法
     * @return
     */
    public static ResultData<Object> success() {
        ResultData result = new ResultData();
        result.setCode(200);
        result.setMsg("成功");
        result.setPageInfo(null);
        //return success(null);
        return result;
    }

    /**
     * 失败时调用的函数
     * @return
     */
    public static ResultData<Object> fail() {
        ResultData result = new ResultData();
        result.setCode(100);
        result.setMsg("失败");
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(T pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
