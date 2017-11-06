package com.ketech.tdo;

import java.io.Serializable;

/**
 * @package: com.ketech.tdo <br/>
 * @class: MessageResultBean <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月01日 <br/>
 * @description: 消息返回类 <br/>.
 */

public class MessageResultBean implements Serializable {

    private Integer code;
    private String msg;

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
}
