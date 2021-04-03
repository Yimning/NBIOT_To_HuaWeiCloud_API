package com.yimning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimning.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yimning
 * @since 2020-09-03
 */
@Repository
//需要@Repository注解找到仓库,否则报错//Could not autowire. No beans of 'StudentMapper' type found.
public interface StudentMapper extends BaseMapper<Student> {

}
