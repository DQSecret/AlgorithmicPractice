import kotlin.system.measureTimeMillis

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶

 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 */
class ClimbStairs {
    /**
     * 废话不多说, 直接上动态规划(DP)
     *
     * 时间复杂度：O(n), 单循环到 n 。
     * 空间复杂度：O(n), dp 数组用了 n 的空间。
     */
    fun solution(n: Int): Int {
        if (n == 1) return 1
        val dp = IntArray(n + 1)
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    /**
     * 斐波那契数
     *
     * 时间复杂度：O(n), 单循环到 n，需要计算第 n 个斐波那契数。
     * 空间复杂度：O(1), 使用常量级空间。
     */
    fun solution2(n: Int): Int {
        if (n == 1) return 1
        var first = 1
        var second = 2
        for (i in 3..n) {
            val third = first + second
            first = second
            second = third
        }
        return second
    }
}

fun main() {
    val item = ClimbStairs()
    val level = 5
    val time = measureTimeMillis {
        val result = item.solution(level)
        println("solution: 爬到${level}层,有${result}种不同的方法")
        val result2 = item.solution2(level)
        println("solution2: 爬到${level}层,有${result2}种不同的方法")
    }
    println("solution in $time ms")
}