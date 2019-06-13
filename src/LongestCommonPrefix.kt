import kotlin.system.measureTimeMillis

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀
 * 如果不存在公共前缀，返回空字符串 ""
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","panda","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀
 *
 * 说明:
 * 所有输入只包含小写字母 a-z
 */
class LongestCommonPrefix {

    /**
     * 最基础方式,先找到最短的,然后遍历,prefix增长
     */
    fun solution(strs: Array<String>): String {
        // 准备参数
        var prefix = ""
        // 1, 最短的
        val minLengthStr = strs.minBy { it.length } ?: ""
        // 2, 循环判断
        for (i in 0..minLengthStr.lastIndex) {
            val aims = minLengthStr[i]
            strs.forEach {
                if (aims != it[i]) {
                    return prefix
                }
            }
            prefix += aims
        }
        // 3, 返回结果
        return prefix
    }

    /**
     * 水平扫描法, 利用 indexOf() !=0 和 substring() 来处理
     */
    fun solution2(strs: Array<String>): String {
        // 1, 空数据判断
        if (strs.isEmpty()) return ""
        // 2, 准备参数
        var prefix = strs[0]
        // 3, 循环判断
        for (i in 1 until strs.size) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length - 1)
                if (prefix.isEmpty()) return ""
            }
        }
        // 4, 返回结果
        return prefix
    }

    /**
     * 比较每个字符串相同列上的字符
     */
    fun solution3(strs: Array<String>): String {
        // 1, 边界处理
        if (strs.isEmpty()) return ""
        if (strs.size == 1) return strs[0]
        // 2, 准备参数
        val prefixSB = StringBuilder()
        // 3, 求最短列数
        var length = Int.MAX_VALUE
        strs.forEach { length = Math.min(length, it.length) }
        // 4, 循环列判断
        for (i in 0 until length) { // 列循环

            var flag = false // 每一列的 char 是否相同
            var aims = '#' // 每一列的 char

            for ((index, str) in strs.withIndex()) { // 每一个 str 循环
                if (index == 0) {
                    aims = str[i]
                    flag = true
                    continue
                }
                if (aims != str[i]) {
                    return prefixSB.toString()
                }
            }

            // 这一列 都相同 则追加至 LCP
            if (flag) {
                prefixSB.append(aims)
            }
        }
        // 3, 返回结果
        return prefixSB.toString()
    }
}

fun main() {
    val strs = arrayOf("flower", "flow", "flight")
    val strs2 = arrayOf("a", "")
    val strs3 = arrayOf("a", "ac")
    val strs4 = arrayOf("dog", "flower", "car")
    val strs5 = arrayOf("aa", "a")
    val strs6 = arrayOf("aca", "cba")
    val time = measureTimeMillis {
        val lcp = LongestCommonPrefix().solution3(strs)
        println("strs lcp is $lcp")
        val lcp2 = LongestCommonPrefix().solution3(strs2)
        println("strs lcp2 is $lcp2")
        val lcp3 = LongestCommonPrefix().solution3(strs3)
        println("strs lcp3 is $lcp3")
        val lcp4 = LongestCommonPrefix().solution3(strs4)
        println("strs lcp4 is $lcp4")
        val lcp5 = LongestCommonPrefix().solution3(strs5)
        println("strs lcp5 is $lcp5")
        val lcp6 = LongestCommonPrefix().solution3(strs6)
        println("strs lcp6 is $lcp6")
    }
    println("solution in $time ms")
}