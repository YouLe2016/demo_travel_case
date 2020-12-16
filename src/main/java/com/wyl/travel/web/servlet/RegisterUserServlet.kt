package com.wyl.travel.web.servlet

import com.fasterxml.jackson.databind.ObjectMapper
import com.wyl.travel.domain.ResultInfo
import com.wyl.travel.domain.User
import com.wyl.travel.service.UserService
import com.wyl.travel.service.impl.UserServiceImpl
import org.apache.commons.beanutils.BeanUtils
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/registerUserServlet")
class RegisterUserServlet : HttpServlet() {
    private val userService: UserService = UserServiceImpl()

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
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
        resp.contentType = "application/json;charset=utf-8"
        resp.writer.write(ObjectMapper().writeValueAsString(info))
    }
}