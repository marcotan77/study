package com.xxx.bizlog.jdbc;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class JdbcUtils {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 组装sql
     *
     * @param table
     * @param params
     * @return
     */
    private String assembleSql(String table, List<PrimaryParam> params) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + table + " WHERE 1=1 ");
        for (PrimaryParam param : params) {
            sql.append(" and " + param.getColumn() + "= ?");
        }
        return sql.toString();
    }

    /**
     * 组装参数
     *
     * @param params
     * @return
     */
    private Object[] assembleParam(List<PrimaryParam> params) {
        return params.stream().map(PrimaryParam::getValue).toArray();
    }


    public Object getByPrimaryKey(QueryWrapper wrapper) {
        List<PrimaryParam> params = Lists.newArrayList(wrapper.getPrimaryParam());
        Class<?> returnClazz = wrapper.getReturnClazz();
        if (CollectionUtils.isEmpty(params)) {
            throw new RuntimeException("没有设置主键字段: " + returnClazz.getName());
        }
        String table = wrapper.getTable();
        return jdbcTemplate.queryForObject(assembleSql(table, params), new BeanPropertyRowMapper<>(returnClazz), assembleParam(params));
    }
}
