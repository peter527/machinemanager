package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.ProjectMapper;
import com.ketech.mapper.StaffMapper;
import com.ketech.po.Project;
import com.ketech.po.Staff;
import com.ketech.tdo.MessageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: StaffService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年10月31日 <br/>
 * @description: 员工Service类 <br/>.
 */

@Service
public class StaffService {

    @Resource
    private StaffMapper staffMapper;
    @Resource
    private ProjectMapper projectMapper;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    /**
     * 分页查询人员信息
     * @param start 开始的条数
     * @param length 查询的条数
     * @param name 姓名参数
     * @return 返回带分页信息的查询结果
     */
    public Map<String, Object> listStaff(int start, int length, String name){
        Staff staff = new Staff();
        if (StringUtils.isNotBlank(name)){
            staff.setStaffName(name);
        }
        PageHelper.offsetPage(start, length);
        List<Staff> staffList = staffMapper.select(staff);
        PageInfo pageInfo = new PageInfo(staffList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", staffList);
        return maps;
    }

    /**
     *
     * @param staff
     * @return
     */
    public Map<String, Object> listAllStaff(Staff staff){
        List<Staff> staffList = staffMapper.select(staff);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("code", SUCCESS_CODE);
        maps.put("data", staffList);
        return maps;
    }

    public Map<String, Object> listStaffAndProject(Staff staff, Project project) {
        List<Staff> staffList = staffMapper.select(staff);
        List<Project> projectList = projectMapper.select(project);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("code", SUCCESS_CODE);
        maps.put("staffList", staffList);
        maps.put("projectList", projectList);
        return maps;
    }

        /**
         * 根据参数搜索人员信息
         * @param staff 人员实体类
         * @return 返回查询结果集合
         */
    private List<Staff> searchStaff(Staff staff){
        List<Staff> staffList = staffMapper.select(staff);
        return staffList;
    }

    /**
     * 保存人员信息
     * @param id 人员编号
     * @param name 人员姓名
     * @param phone 联系方式
     * @param operationType 操作类型：0.新增，1.更新
     * @return 返回操作结果信息
     */
    public MessageResultBean saveStaff(String id, String name, String phone, String operationType){
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        Staff staff = new Staff();
        staff.setStaffId(id);
        staff.setStaffName(name);
        staff.setStaffPhone(phone);
        if ("0".equals(operationType)){
            staff.setStaffStatus("0");
            staff.setCreateDate(new Date());
            String checkMessage = checkIdAndName(id, name);
            if (StringUtils.isNotBlank(checkMessage)){
                resultMessage = checkMessage;
            } else {
                if (staffMapper.insert(staff) > 0){
                    code = SUCCESS_CODE;
                    resultMessage = "新增人员成功";
                }
            }
        } else {
            staff.setModifyDate(new Date());
            if (staffMapper.updateByPrimaryKeySelective(staff) > 0){
                code = SUCCESS_CODE;
                resultMessage = "修改人员成功";
            }
        }
        messageResult.setCode(code);
        messageResult.setMsg(resultMessage);
        return messageResult;
    }

    /**
     * 检测人员编号跟与人员姓名是否存在
     * @param id 待检测人员编号
     * @param name 待检测人员姓名
     * @return 返回检测信息：不存在返回空字符串，否则返回对应的信息
     */
    private String checkIdAndName(String id, String name){
        String result = "";
        Staff idStaff = new Staff();
        idStaff.setStaffId(id);
        if (searchStaff(idStaff).size() > 0){
            result = "人员编号已存在";
        }
        Staff nameStaff = new Staff();
        nameStaff.setStaffName(name);
        if(searchStaff(nameStaff).size() > 0){
            result = "人员姓名已存在";
        }
        return result;
    }

}
