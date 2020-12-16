package com.wyl.travel.mapper

import com.wyl.base.utils.getSqlSession
import com.wyl.travel.domain.User
import kotlin.test.Test


class UserMapperTest {

    @Test
    fun testFindByUsername() {
        getSqlSession().use {
            val mapper = it.getMapper(UserMapper::class.java)
            println(mapper.findByUsername("lisi12351"))
        }
    }

    @Test
    fun testSaveUser() {
        getSqlSession().use {
            val mapper = it.getMapper(UserMapper::class.java)
            mapper.saveUser(
                User(0, "lisi12351", "123456789", "lisi", "2000-01-01", "男", "15815635468", "dsaf@sq.com", "n", "1562")
            )
            it.commit()
        }
    }

    @Test
    fun testFindByUsernameAndPassword() {
        getSqlSession().use {
            val mapper = it.getMapper(UserMapper::class.java)
            val user = User().apply {
                username = "yinsehuohu"
                password = "123456781"
            }
            println(mapper.findByUsernameAndPassword(user))
        }
    }
}