package com.ketech.mapper;

import com.ketech.po.OperationLog;
import com.ketech.vo.OperationLogBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package: com.ketech.mapper <br/>
 * @class: OperationLogInfoMapper <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月10日 <br/>
 * @description: 操作日志信息Mapper <br/>.
 */

@Repository
public interface OperationLogInfoMapper {

    List<OperationLogBean> selectOperationLogInfo(OperationLog operationLog);

}
