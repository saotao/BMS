package stao.BMS.entity.vo;

import lombok.Data;

@Data
public class ResponseResult<T>{

    //状态码
    private int code;

    //数据
    private T data;

    //信息
    private String msg;

    public ResponseResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
