package com.wyl.travel.mapper

import com.wyl.base.utils.getSqlSession
import org.junit.Test

class CategoryMapperTest {
    @Test
    fun testFindAll() {
        getSqlSession().use {
            val mapper = it.getMapper(CategoryMapper::class.java)
            mapper.findAll().forEach(::println)
        }
    }
}