package com.wyl.travel.service

import com.wyl.travel.domain.User

interface UserService {
    /**
     * 注册用户的方法
     */
    fun register(user: User): Boolean
    fun login(user: User): User?
}