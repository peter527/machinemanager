package com.ketech.exception;

/**
 * @package: com.ketech.exception <br/>
 * @class: MachineCountException <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月11日 <br/>
 * @description: 设备数量不足异常 <br/>.
 */

public class MachineCountException extends RuntimeException{

    public MachineCountException() {
    }

    public MachineCountException(String message) {
        super(message);
    }

    public MachineCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public MachineCountException(Throwable cause) {
        super(cause);
    }

    public MachineCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
