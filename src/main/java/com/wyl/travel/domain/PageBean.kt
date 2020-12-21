package com.wyl.travel.domain

data class PageBean<T>(
    var totalPage: Int = 0,
    var currentPage: Int = 0,
    var pageSize: Int = 0,
    var totalCount: Int = 0,
    var dataList: List<T> = emptyList(),
)