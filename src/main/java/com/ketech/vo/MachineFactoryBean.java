package com.ketech.vo;

import com.ketech.po.Factory;
import com.ketech.po.Machine;

/**
 * @package: com.ketech.vo <br/>
 * @class: MachineFactoryBean <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 设备信息类 <br/>.
 */

public class MachineFactoryBean extends Machine{

    private Factory factory;

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
