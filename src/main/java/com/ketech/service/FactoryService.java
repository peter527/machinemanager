package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.FactoryMapper;
import com.ketech.po.Factory;
import com.ketech.tdo.MessageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: FactoryService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 厂商Service <br/>.
 */

@Service
public class FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    /**
     * 分页查询厂商信息
     * @param start 开始的条数
     * @param length 查询的条数
     * @param name 厂商名称参数
     * @return 返回带分页信息的查询结果
     */
    public Map<String, Object> listFactory(int start, int length, String name){
        Factory factory = new Factory();
        if (StringUtils.isNotBlank(name)){
            factory.setFactoryName(name);
        }
        PageHelper.offsetPage(start, length);
        List<Factory> factoryList = factoryMapper.select(factory);
        PageInfo pageInfo = new PageInfo(factoryList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", factoryList);
        return maps;
    }

    /**
     * 根据参数搜索厂商信息
     * @param factory 厂商实体类
     * @return 返回查询结果集合
     */
    private List<Factory> searchFactory(Factory factory){
        List<Factory> factoryList = factoryMapper.select(factory);
        return factoryList;
    }

    /**
     * 保存厂商信息
     * @param factoryId 厂商编号
     * @param factoryName 厂商名称
     * @param chargerName 负责人姓名
     * @param chargerPhone 负责人联系方式
     * @param operationType 操作类型：0.新增，1.更新
     * @return 返回操作结果信息
     */
    public MessageResultBean saveFactory(String factoryId, String factoryName, String chargerName, String chargerPhone, String operationType){
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        Factory factory = new Factory();
        factory.setFactoryId(factoryId);
        factory.setFactoryName(factoryName);
        factory.setFactoryCharger(chargerName);
        factory.setChargerPhone(chargerPhone);
        if ("0".equals(operationType)){
            factory.setStatus("0");
            factory.setCreateDate(new Date());
            String checkMessage = checkIdAndName(factoryId, factoryName);
            if (StringUtils.isNotBlank(checkMessage)){
                resultMessage = checkMessage;
            } else {
                if (factoryMapper.insert(factory) > 0){
                    code = SUCCESS_CODE;
                    resultMessage = "新增厂商成功";
                }
            }
        } else {
            factory.setModifyDate(new Date());
            if (factoryMapper.updateByPrimaryKeySelective(factory) > 0){
                code = SUCCESS_CODE;
                resultMessage = "修改厂商成功";
            }
        }
        messageResult.setCode(code);
        messageResult.setMsg(resultMessage);
        return messageResult;
    }

    /**
     * 检测厂商编号跟与厂商名称是否存在
     * @param id 待检测厂商编号
     * @param name 待检测厂商名称
     * @return 返回检测信息：不存在返回空字符串，否则返回对应的信息
     */
    private String checkIdAndName(String id, String name){
        String result = "";
        Factory idFactory = new Factory();
        idFactory.setFactoryId(id);
        if (searchFactory(idFactory).size() > 0){
            result = "厂商编号已存在";
        }
        Factory nameFactory = new Factory();
        nameFactory.setFactoryName(name);
        if(searchFactory(nameFactory).size() > 0){
            result = "厂商姓名已存在";
        }
        return result;
    }


}
