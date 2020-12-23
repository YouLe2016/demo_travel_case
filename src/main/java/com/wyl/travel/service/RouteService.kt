package com.wyl.travel.service

import com.wyl.travel.domain.PageBean
import com.wyl.travel.domain.Route

interface RouteService {
    fun pageQuery(curPage: Int, size: Int, cid: Int, rname: String): PageBean<Route>
    fun findOne(rid: Int): Route
}