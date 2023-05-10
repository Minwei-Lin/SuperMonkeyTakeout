package com.minwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minwei.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 地址管理 Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
