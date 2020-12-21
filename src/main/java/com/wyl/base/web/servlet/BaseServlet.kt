package com.wyl.base.web.servlet

import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class BaseServlet : HttpServlet() {
    private val jacksonMapper = ObjectMapper()

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        this.doPost(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
//        println(req.requestURL.toString())
//        println(req.requestURI)
        val methodName = req.requestURI.substring(req.requestURI.lastIndexOf("/") + 1)
//        println(methodName)
        try {
            this::class.java.getMethod(
                methodName,
                HttpServletRequest::class.java,
                HttpServletResponse::class.java
            ).invoke(this, req, resp)
        } catch (e: Exception) {
            println("没有这样的方法 -- ${req.requestURI}")
            e.printStackTrace()
        }
    }

    protected fun writeJson(any: Any?, resp: HttpServletResponse) {
        resp.contentType = "application/json;charset=utf-8"
        resp.writer.write(jacksonMapper.writeValueAsString(any))
    }
}