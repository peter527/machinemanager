package com.ketech.mapper;

import com.ketech.po.Location;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface LocationMapper extends Mapper<Location> {
}