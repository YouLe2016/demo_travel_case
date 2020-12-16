package com.wyl.travel.web.servlet

import com.fasterxml.jackson.databind.ObjectMapper
import com.wyl.travel.domain.ResultInfo
import com.wyl.travel.domain.User
import com.wyl.travel.service.UserService
import com.wyl.travel.service.impl.UserServiceImpl
import org.apache.commons.beanutils.BeanUtils
import java.lang.reflect.InvocationTargetException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/loginServlet")
class LoginServlet : HttpServlet() {
    private val userService: UserService = UserServiceImpl()

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val user = User()
        try {
            BeanUtils.populate(user, req.parameterMap)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        val u: User? = userService.login(user)
        val info = ResultInfo()
        when {
            u == null -> {
                info.isFlag = false
                info.errorMsg = "用户名或密码错误"
            }
            "Y" != u.status -> {
                info.isFlag = false
                info.errorMsg = "您尚未激活，请登录邮箱激活"
            }
            else -> {
                info.isFlag = true
            }
        }
        resp.contentType = "application/json;charset=utf-8"
        resp.writer.write(ObjectMapper().writeValueAsString(info))
    }
}