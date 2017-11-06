package com.ketech.mapper;

import com.ketech.po.Staff;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface StaffMapper extends Mapper<Staff> {
}