package com.ketech.mapper;

import com.ketech.po.Location;
import com.ketech.vo.LocationInfoBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationInfoMapper {

    List<LocationInfoBean> selectLocationInfo(Location location);

}