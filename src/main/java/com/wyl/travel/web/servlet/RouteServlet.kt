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
            getParameter("cid").toIntOrNull()?.let { cid ->
                var curPage = getParameter("currentPage")?.toIntOrNull()
                if (curPage == null) curPage = 1
                var pageSize = getParameter("pageSize")?.toIntOrNull()
                if (pageSize == null) pageSize = 5
                val pageBean = routeService.pageQuery(curPage, pageSize, cid)
                writeJson(pageBean, resp)
            }
        }
    }
}