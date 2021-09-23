package adventofcode.y2015.d4

import java.math.BigInteger
import java.security.MessageDigest

/**
 * 题目的意思,简单来说,貌似一个循环就搞定了?
 *
 * @author DQ For Olivia
 * @since 2021/9/23 4:15 下午
 * @see <a href="https://adventofcode.com/2015/day/4">文章</a>
 */
class TheIdealStockingStuffer {

    companion object {
        const val puzzle: String = "bgvyzdsv"
    }

    /**
     * (input+key).md5().startsWith("00000") == true
     * 求 key 的最小值
     */
    fun part1(input: String = puzzle): Int {
        var target = -1
        for (i in 1..Int.MAX_VALUE) {
            "$input${i}".md5().takeIf {
                it.startsWith("00000")
            }?.let {
                target = i
                println("[$puzzle - ${i}]的md5值为: $it")
            }
            if (target > -1) break

        }
        return target
    }

    /**
     * 前几位,这个要求,需要参数传递
     */
    fun part2(input: String = puzzle, start: Int = 6): Int {
        var target = -1
        val require = "0".padStart(start, '0')
        for (i in 1..Int.MAX_VALUE) {
            "$input${i}".md5().takeIf {
                it.startsWith(require)
            }?.let {
                target = i
                println("[$puzzle - ${i}]的md5值为: $it")
            }
            if (target > -1) break

        }
        return target
    }
}

fun main() {
    TheIdealStockingStuffer().run {
        part1()
        part2()
    }
}

/**
 * 求 MD5 值
 */
fun String.md5(): String {
    return MessageDigest.getInstance("MD5")
        .digest(toByteArray())
        .let { BigInteger(1, it) }
        .toString(16)
        .padStart(32, '0')
}