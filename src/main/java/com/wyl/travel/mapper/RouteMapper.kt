package com.wyl.travel.mapper

import com.wyl.travel.domain.Route

interface RouteMapper {
    /**
     * 查询线路总数
     */
    fun findRouteCount(map: MutableMap<String, Any?>): Int

    /**
     * 分页查询数据
     */
    fun pageQuery(map: MutableMap<String, Any?>): List<Route>

    /**
     * 根据[rid]查询Route对象
     */
    fun findRouteById(rid: Int): Route
}