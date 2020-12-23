package com.wyl.travel.mapper

import com.wyl.base.utils.getSqlSession
import kotlin.test.Test

class RouteMapperTest {

    @Test
    fun testFindRouteById() {
        getSqlSession().use {
            val mapper = it.getMapper(RouteMapper::class.java)
            val route = mapper.findRouteById(1)
            println(route.rid)
            println(route.rname)
            println(route.routeIntroduce)
            println(route.category)
            println(route.seller)
            println(route.routeImgList)
        }
    }

}