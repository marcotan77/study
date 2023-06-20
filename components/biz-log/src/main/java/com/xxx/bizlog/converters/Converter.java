package com.xxx.bizlog.converters;

/**
 * 转换器
 *
 * @param <P>
 * @author dngzs
 * @date 2023年05月26日13:55:43
 */
public interface Converter<P, R> {

    R convert(P p);
}
