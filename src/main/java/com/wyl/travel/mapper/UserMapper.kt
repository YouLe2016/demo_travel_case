package com.wyl.travel.mapper

import com.wyl.travel.domain.User

interface UserMapper {

    /**
     * 根据用户名查询用户信息
     */
    fun findByUsername(username: String): User?

    /**
     * 根据用户名查询用户信息
     */
    fun findByUsernameAndPassword(user: User): User?

    /**
     * 保存用户
     */
    fun saveUser(user: User)


}