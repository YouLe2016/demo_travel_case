package com.wyl.travel.web.servlet

import com.wyl.base.web.servlet.BaseServlet
import com.wyl.travel.service.RouteService
import com.wyl.travel.service.impl.RouteServiceImpl
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/route/*")
class RouteServlet : BaseServlet() {
    private val routeService: RouteService = RouteServiceImpl()

    fun findAll(req: HttpServletRequest, resp: HttpServletResponse) {
        req.apply {
            val cid = getParameter("cid")?.toIntOrNull() ?: -1
            val rname = getParameter("rname") ?: ""
            val curPage = getParameter("currentPage")?.toIntOrNull() ?: 1
            val pageSize = getParameter("pageSize")?.toIntOrNull() ?: 5
            val pageBean = routeService.pageQuery(curPage, pageSize, cid, rname)
            writeJson(pageBean, resp)
        }
    }

    fun findOne(req: HttpServletRequest, resp: HttpServletResponse) {
        val rid = req.getParameter("rid")?.toIntOrNull() ?: -1
        val route = routeService.findOne(rid)
        writeJson(route, resp)
    }
}