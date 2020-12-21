package com.wyl.travel.web.servlet

import com.wyl.base.web.servlet.BaseServlet
import com.wyl.travel.domain.ResultInfo
import com.wyl.travel.domain.User
import com.wyl.travel.service.UserService
import com.wyl.travel.service.impl.UserServiceImpl
import org.apache.commons.beanutils.BeanUtils
import java.lang.reflect.InvocationTargetException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/user/*")
class UserServlet : BaseServlet() {
    private val userService: UserService = UserServiceImpl()

    fun login(req: HttpServletRequest, resp: HttpServletResponse) {
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
                req.session.setAttribute("user", u)
            }
        }
        writeJson(info, resp)
    }

    fun register(req: HttpServletRequest, resp: HttpServletResponse) {
        val checkCode: String? = req.getParameter("check")
        val checkNet: String? = req.session.getAttribute("CHECKCODE_SERVER") as String
        val info = ResultInfo()
        if (!checkCode.equals(checkNet, true)) {
            info.isFlag = false
            info.errorMsg = "验证码错误!"
        } else {
            val user = User()
            try {
                BeanUtils.populate(user, req.parameterMap)
                println(user)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val flag = userService.register(user)
            info.isFlag = flag
            if (flag) {
                req.session.removeAttribute("CHECKCODE_SERVER") //为了保证验证码只能使用一次
            } else {
                info.errorMsg = "注册失败!"
            }
        }
        writeJson(info, resp)
    }

    fun findUser(req: HttpServletRequest, resp: HttpServletResponse) {
        val user = req.session.getAttribute("user") as User?
        writeJson(user, resp)
    }

    fun exit(req: HttpServletRequest, resp: HttpServletResponse) {
        req.session.invalidate()
        resp.sendRedirect(req.contextPath + "/login.html")
    }
}