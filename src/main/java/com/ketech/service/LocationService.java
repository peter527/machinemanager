package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.LocationInfoMapper;
import com.ketech.mapper.LocationMapper;
import com.ketech.po.Location;
import com.ketech.tdo.MessageResultBean;
import com.ketech.vo.LocationInfoBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: LocationService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 项目地点Service <br/>.
 */

@Service
public class LocationService {

    @Resource
    private LocationMapper locationMapper;
    @Resource
    private LocationInfoMapper locationInfoMapper;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public Map<String, Object>ListLocationInfo(int start, int length, Location location){
        PageHelper.offsetPage(start, length);
        List<LocationInfoBean> locationInfoList = locationInfoMapper.selectLocationInfo(location);
        PageInfo pageInfo = new PageInfo(locationInfoList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", locationInfoList);
        return maps;
    }

    public Map<String, Object> listLocationByLocation(Location location){
        List<Location> locationList = locationMapper.select(location);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("code", SUCCESS_CODE);
        maps.put("data", locationList);
        return maps;
    }

    private List<Location> searchLocation(Location location){
        List<Location> locationList = locationMapper.select(location);
        return locationList;
    }

    public MessageResultBean saveLocation(Location location, String operationType){
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        if ("0".equals(operationType)){
            location.setStatus("0");
            location.setCreateDate(new Date());
            String checkMessage = checkIdAndName(location.getLocationId(), location.getLocationName());
            if (StringUtils.isNotBlank(checkMessage)){
                resultMessage = checkMessage;
            } else {
                if (locationMapper.insert(location) > 0){
                    code = SUCCESS_CODE;
                    resultMessage = "新增设备成功";
                }
            }
        } else {
            location.setModifyDate(new Date());
            if (locationMapper.updateByPrimaryKeySelective(location) > 0){
                code = SUCCESS_CODE;
                resultMessage = "修改设备成功";
            }
        }
        messageResult.setCode(code);
        messageResult.setMsg(resultMessage);
        return messageResult;
    }

    private String checkIdAndName(String id, String name){
        String result = "";
        Location idLocation = new Location();
        idLocation.setLocationId(id);
        if (searchLocation(idLocation).size() > 0){
            result = "项目点编号已存在";
        }
        Location nameLocation = new Location();
        nameLocation.setLocationName(name);
        if(searchLocation(nameLocation).size() > 0){
            result = "项目点名称已存在";
        }
        return result;
    }
}
