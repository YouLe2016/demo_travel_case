package com.wyl.travel.mapper

import com.wyl.base.utils.getSqlSession
import kotlin.test.Test

class SellerMapperTest {

    @Test
    fun testFindImageById() {
        getSqlSession().use {
            val mapper = it.getMapper(SellerMapper::class.java)
            val seller = mapper.findSellerById(1)
            println(seller)
        }
    }

}