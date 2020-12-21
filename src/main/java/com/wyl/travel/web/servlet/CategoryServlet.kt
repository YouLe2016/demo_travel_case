package com.wyl.travel.web.servlet

import com.wyl.base.utils.JedisUtil
import com.wyl.base.web.servlet.BaseServlet
import com.wyl.travel.domain.Category
import com.wyl.travel.service.CategoryService
import com.wyl.travel.service.impl.CategoryServiceImpl
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private const val RedisKey = "tab_category"

@WebServlet("/category/*")
class CategoryServlet : BaseServlet() {
    private val categoryService: CategoryService = CategoryServiceImpl()

    fun findAll(req: HttpServletRequest, resp: HttpServletResponse) {
        val categories: List<Category>
        JedisUtil.getJedis().use {
            val set = it.zrangeWithScores(RedisKey, 0, -1)
            if (set.isEmpty()) {
                println("CategoryServlet -- 从数据库获取数据")
                categories = categoryService.findAll()
                val mutableMap = mutableMapOf<String, Double>()
                categories.forEach { item ->
                    mutableMap[item.cname] = item.cid.toDouble()
                }
                it.zadd(RedisKey, mutableMap)
            } else {
                println("CategoryServlet -- 从Redis获取数据")
                categories = set.map { item ->
                    Category(item.score.toInt(), item.element)
                }
            }
        }
        writeJson(categories, resp)
    }
}