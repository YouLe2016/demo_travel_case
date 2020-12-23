package com.wyl.travel.mapper

import com.wyl.base.utils.getSqlSession
import kotlin.test.Test

class RouteImgMapperTest {

    @Test
    fun testFindImageById() {
        getSqlSession().use {
            val mapper = it.getMapper(RouteImgMapper::class.java)
            val routes = mapper.findImageById(1)
            println(routes.size)
            routes.forEach { item ->
                println("----------------")
                println(item.rid)
                println(item.rgid)
                println(item.bigPic)
                println(item.smallPic)
            }
        }
    }

    @Test
    fun testFindSize() {
        getSqlSession().use {
            val mapper = it.getMapper(RouteImgMapper::class.java)
            val routes = mapper.findImageById(1)
            println(routes.size)
            routes.forEach { item ->
                println("----------------")
                println(item.rid)
                println(item.rgid)
                println(item.bigPic)
                println(item.smallPic)
            }
        }
    }

}