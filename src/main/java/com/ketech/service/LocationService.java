package com.ketech.service;

import com.ketech.po.Location;
import org.springframework.stereotype.Service;

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


    public Map<String, Object>ListLocationInfo(int start, int length, Location location){
        Location location1 = new Location();
        System.out.println("Here");
        /*if (StringUtils.isNotBlank(name)){
            project.setProjectName(name);
        }
        PageHelper.offsetPage(start, length);
        List<Project> projectList = projectMapper.select(project);
        PageInfo pageInfo = new PageInfo(projectList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", projectList);*/
        return null;
    }
}
