package com.wyl.base

import com.wyl.base.utils.JedisUtil
import org.junit.Test

/**
 * 对应关系                         增                           查                  删
 * string 对应Java String         set key value               get                 del
 * hash 对应Java HashMap          set key filed value         hget                hdel
 * list 对应Java ArrayList        lpush/rpush key value       lrange              lpop
 * set 对应Java HashSet           sadd key value              smembers            srem
 * sortedset 对应LinkedHashSet    zadd key score value        zrange              zrem
 */

class JedisTest {
    @Test
    fun testString() {
        // string 对应Java String
        JedisUtil.getJedis().use {
            val key = "string"
            it.del(key)
            println(it.get(key))
            it.set(key, "$key 的存储格式为：set key value")
            println(it.get(key))
        }
    }

    @Test
    fun testHash() {
        // hash 对应Java HashMap
        JedisUtil.getJedis().use {
            val key = "hash"
            it.del(key)
//            it.hdel(key, "format")
            println(it.hgetAll(key)::class.java)
            it.hset(key, "format", "$key 的存储格式为：set key filed value")
            it.hset(key, "username", "lisi")
            it.hset(key, "password", "123456")
            it.hgetAll(key).forEach { (t, u) ->
                println("$t --> $u")
            }
        }
    }

    @Test
    fun testList() {
        // list 对应Java ArrayList
        JedisUtil.getJedis().use {
            val key = "list"
            it.del(key)
//            it.lpop(key)
            println(it.lrange(key, 0, -1)::class.java)
            it.lpush(key, "$key 的存储格式为：lpush/rpush key value")
            it.rpush(key, "1", "2", "3")
            it.lrange(key, 0, -1).forEach(::println)
        }
    }

    @Test
    fun testSet() {
        // set 对应Java HashSet
        JedisUtil.getJedis().use {
            val key = "set"
//            it.del(key)
            it.srem(key, "1", "2", "3")
            println(it.smembers(key)::class.java)
            it.sadd(key, "$key 的存储格式为：sadd key value")
            it.sadd(key, "1", "2", "3")
            it.sadd(key, "$key 的查询式为：smembers")
            it.smembers(key).forEach(::println)
        }
    }

    @Test
    fun testSortedSet() {
        // sortedset 对应LinkedHashSet
        JedisUtil.getJedis().use {
            val key = "sortedset"
            it.del(key)
//            it.lpop(key)
            println(it.zrange(key, 0, -1)::class.java)
            println(it.zrange(key, 0, -1))
            it.zadd(key, 0.0, "$key 的存储格式为：zadd key score value")
            it.zadd(key, mutableMapOf("1" to 1.0, "2" to 2.0, "3" to 3.0))
            it.zadd(key, 4.0, "$key 的查询式为：zrange")
//            it.zrange(key, 0, -1).forEach(::println)
            it.zrangeWithScores(key, 0, -1).forEach { item ->
                println("${item.score} -- ${item.element}")
            }
        }
    }
}