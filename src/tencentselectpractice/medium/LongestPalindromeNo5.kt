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
     * l+1 < r-1 : 这个情况需要判断, 反之 r-l<=2 则不需要判断
     * s[l][r] =  (s[l] == s[r]) && (r - l <= 2)
     * if r-l>2 则需要判断子串, 故, 结论如下
     *
     * 1、定义 “状态” : s[l][r] 代表着, s 中 l..r 的子串是回文串
     * 2、找到 “状态转移方程” : s[l][r] =  (s[l] == s[r]) && ( (r - l <= 2) || s[l+1][r-1])
     */
    @Suppress("KDocUnresolvedReference")
    fun solution2(s: String): String {
        val len = s.length
        if (len <= 1) return s

        var longest = 1 // 长度
        var lps = s.substring(0, 1) // 子串
        val dp = Array(len) { BooleanArray(len) } // 二维数组, 保存状态

        for (r in 1 until len) {
            for (l in 0 until r) {
                /*
                if (s[l] == s[r]) {
                    if (r - l <= 2) {
                        dp[l][r] = true
                    } else {
                        dp[l][r] = dp[l + 1][r - 1]
                    }
                }
                转化为, 下面的代码
                */
                if (s[l] == s[r] && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true
                    if (r - l + 1 > longest) {
                        longest = r - l + 1
                        lps = s.substring(l, r + 1)
                    }
                }
            }
        }
        return lps
    }

    /**
     * Manacher 算法
     */
    fun solution3(s: String): String {
        TODO("马拉车算法, 一知半解, 之后再说")
    }
}

fun main() {
    testLongestPalindrome()
}

fun testLongestPalindrome() {
    LongestPalindrome().also {
        var aims = "a"
        println(it.solution2(aims))
        aims = "bb"
        println(it.solution2(aims))
        aims = "ccc"
        println(it.solution2(aims))
        aims = "ddde"
        println(it.solution2(aims))
        aims = "efgfed"
        println(it.solution2(aims))
        aims = "hiihqwe"
        println(it.solution2(aims))
    }
}