package com.wyl.travel.mapper

import com.wyl.travel.domain.Seller

interface SellerMapper {
    fun findSellerById(sid: Int): Seller?
}