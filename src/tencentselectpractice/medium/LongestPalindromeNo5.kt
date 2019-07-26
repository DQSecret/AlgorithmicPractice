package tencentselectpractice.medium

import kotlin.math.max

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。

 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
class LongestPalindrome {

    /**
     * 关键, 1-回文子串, 2-为什么要设置最大长度?
     */

    /**
     * 暴力法: 遍历所有子串, 然后依次判断, 是否为回文串, 记录最长的
     * 方法 1: 从中心向两边判断
     *      abad
     *     _ab      左右不等, 下一个
     *      aba     左右相等, 记录, 并继续
     *     _abad    左右不等, 下一个
     *       fad    左右不等, 下一个
     *        ad_   左右不等, 下一个
     * 结论: aba
     *
     * Tips: 中心个数,
     *      如果 s 的 length 为奇数, 中心为 n
     *      如果 s 的 length 为偶数, 中心为 n-1,
     *          |--- abba: a_b,b_b,b_a 3 个中心
     * 结论: abba 一共需要有 2n-1 个中心 (a_b_b_a)
     * 方法: 因为每次都需要左右比较, 所以利用两个下标,
     *          |-- 如果中心是一个存在的值, 下标都指向自己
     *                  |-- (a) 则下标是, 0:0
     *                  |-- (b) 则下标是, 1:1
     *                  |-- ...
     *          |-- 如果中心是    _     , 下标指向左右
     *                  |-- (_) 则下标是, 0:1
     *                  |-- (_) 则下标是, 1:2
     *                  |-- ...
     * 结论: 发现 1(存在的值) 和 _(两值中间) 的左下标都相同, 所以可以合并到一次循环体内
     * 循环: 条件判断, 左右下标
     */
    fun solution1(s: String): String {
        // 特殊情况
        if (s.isEmpty()) return ""
        if (s.length == 1) return s
        // 下标
        var start = 0
        var end = 0
        // 循环体, i 存在的值的下标
        for (i in 0 until s.length) {
            val len1 = expandAroundCenter(s, i, i) // 存在的值
            val len2 = expandAroundCenter(s, i, i + 1) // 亮值之间的 _
            val len = max(len1, len2) // 取本次循环的较大回文长度
            if (len > end - start) { // 和之前的回文长度对比, 取大值
                start = i - (len - 1) / 2
                end = i + len / 2
                // 长度为 1 则是: [当前坐标]..[当前坐标]
                // 长度为 2 则是: [当前坐标]..[当前坐标+1]
                // 长度为 3 则是: [当前坐标-1]..[当前坐标+1]
                // 长度为 4 则是: [当前坐标-1]..[当前坐标+2]
                // 长度为 5 则是: [当前坐标-2]..[当前坐标+2]
                // 总结规律
            }
        }
        return s.substring(start, end + 1)
    }

    /**
     * 从中心点的 左右 or 自己 开始向左右扩展, 计算回文长度
     */
    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var l = left
        var r = right
        // l >= 0 && r < s.length 是为了, 防止 数组下标越界
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--
            r++
        }
        // 计算此回文的长度
        return r - l - 1
    }

    /**
     * 动态规划(DP): 解决这类 “最优子结构” 问题, 可以考虑使用 “动态规划”
     *
     * 1、定义 “状态”
     * 2、找到 “状态转移方程”
     */
    fun solution2(s: String): String {

    }
}

fun main() {
    testLongestPalindrome()
}

fun testLongestPalindrome() {
    LongestPalindrome().also {
        var aims = "a"
        println(it.solution1(aims))
        aims = "bb"
        println(it.solution1(aims))
        aims = "ccc"
        println(it.solution1(aims))
        aims = "ddde"
        println(it.solution1(aims))
        aims = "efgfed"
        println(it.solution1(aims))
        aims = "hiihqwe"
        println(it.solution1(aims))
    }
}