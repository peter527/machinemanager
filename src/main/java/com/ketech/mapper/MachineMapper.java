package com.ketech.mapper;

import com.ketech.po.Machine;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MachineMapper extends Mapper<Machine> {
}