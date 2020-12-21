package com.wyl.travel.mapper

import com.wyl.travel.domain.Category

interface CategoryMapper {
    /**
     * 查询所有分类
     */
    fun findAll(): List<Category>
}