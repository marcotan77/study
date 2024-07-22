package com.tan.mapper;

import com.tan.model.Blog;

/**
 * @author tanchusheng
 * @date 2023/10/20 11:24
 */
public interface BlogMapper {

    Blog selectBlog(String id);
}
