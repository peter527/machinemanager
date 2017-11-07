package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.LocationMapper;
import com.ketech.mapper.LocationStaffProjectMapper;
import com.ketech.po.Location;
import com.ketech.vo.LocationInfoBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private LocationStaffProjectMapper locationInfoMapper;

    public Map<String, Object>ListLocationInfo(int start, int length, Location location){
        PageHelper.offsetPage(start, length);
        List<LocationInfoBean> locationInfoList = locationInfoMapper.selectProjectInfoByProject(location);
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
}
