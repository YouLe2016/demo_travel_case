package com.wyl.travel.service.impl

import com.wyl.base.utils.getSqlSession
import com.wyl.travel.domain.PageBean
import com.wyl.travel.domain.Route
import com.wyl.travel.mapper.RouteMapper
import com.wyl.travel.service.RouteService
import kotlin.math.max
import kotlin.math.min

class RouteServiceImpl : RouteService {
    override fun pageQuery(curPage: Int, size: Int, cid: Int): PageBean<Route> {
        return getSqlSession().use {
            val mapper = it.getMapper(RouteMapper::class.java)
            val totalCount = mapper.findRouteCount()
            val totalPage = totalCount / size + min(totalCount % size, 1)
            val curPageFix = min(totalPage, max(curPage, 1))

            val map = mutableMapOf<String, Any>(
                "start" to max(curPageFix - 1, 0) * size,
                "size" to size,
                "cid" to cid
            )
            val list = mapper.pageQuery(map)
            PageBean(
                totalPage = totalPage,
                currentPage = curPageFix,
                pageSize = size,
                totalCount = totalCount,
                dataList = list,
            )
        }
    }
}