package com.wyl.travel.service.impl

import com.wyl.base.utils.UuidUtil
import com.wyl.base.utils.getSqlSession
import com.wyl.travel.domain.User
import com.wyl.travel.mapper.UserMapper
import com.wyl.travel.service.UserService

class UserServiceImpl : UserService {

    override fun register(user: User): Boolean {
        return getSqlSession().use {
            val mapper = it.getMapper(UserMapper::class.java)
            val u = mapper.findByUsername(user.username)
            if (u != null) {
                false
            } else {
                user.status = "Y"
                user.code = UuidUtil.getUuid()
                mapper.saveUser(user)
                it.commit()
                true
                // 发送邮件  省略、、、
            }
        }
    }

    override fun login(user: User): User? {
        return getSqlSession().use {
            val mapper = it.getMapper(UserMapper::class.java)
            mapper.findByUsernameAndPassword(user)
        }
    }
}