package com.testSpringMini.demo.common;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * mybatis的mapper的统一父类，用于简单sql语句的快速编码
 * @param <T>
 */


public interface MySqlExtensionMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
