package com.wyl.travel.mapper

import com.wyl.travel.domain.Route

interface RouteMapper {
    /**
     * 查询线路总数
     */
    fun findRouteCount(): Int

    /**
     * 分页查询数据
     */
    fun pageQuery(map: MutableMap<String, Any>): List<Route>
}