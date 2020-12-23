package com.wyl.travel.mapper

import com.wyl.travel.domain.RouteImg

interface RouteImgMapper {
    /**
     * 根据旅游路线[rid]，查询旅游图片
     */
    fun findImageById(rid: Int): List<RouteImg>
}