package com.wyl.travel.service

import com.wyl.travel.domain.Category

interface CategoryService {
    fun findAll(): List<Category>
}
