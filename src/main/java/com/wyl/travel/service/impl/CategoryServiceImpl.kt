package com.wyl.travel.service.impl

import com.wyl.base.utils.getSqlSession
import com.wyl.travel.domain.Category
import com.wyl.travel.mapper.CategoryMapper
import com.wyl.travel.service.CategoryService

class CategoryServiceImpl : CategoryService {
    override fun findAll(): List<Category> {
        return getSqlSession().use {
            val mapper = it.getMapper(CategoryMapper::class.java)
            mapper.findAll()
        }
    }
}