package dp

import kotlin.system.measureTimeMillis

/**
 * 动态规划的类型练习
 */

/**
 * 一个人每次只能走一层楼梯或者两层楼梯,
 * 问走到第80层楼梯一共有多少种方法.
 *
 * 设DP[i]为走到第i层一共有多少种方法,那么DP[80]即为所求.
 * 很显然:
 * DP[1]=1; // 走到第一层只有一种方法, 就是走一层楼梯
 * DP[2]=2; // 走到第二层有两种方法: 走两次一层楼梯或者走一次两层楼梯
 * 同理,走到第i层楼梯,可以从i-1层走一层,或者从i-2走两层.
 *
 * 很容易得到:
 * 递推公式：DP[i]=DP[i-1]+DP[i-2]
 * 边界条件：DP[1]=1   DP[2]=2
 */
class PaLouTi {

    // 临时定死的
    private val dp = LongArray(81)

    /**
     * 自顶向下的解法
     */
    fun solution2Down(level: Int): Long {
        if (level < 1) return 0
        if (level == 1) return 1
        if (level == 2) return 2
        return solution2Down(level - 1) + solution2Down(level - 2)
    }

    /**
     * 自底向上的解法
     */
    fun solution2Up(level: Int): Long {
        val dp = LongArray(level + 1)
        dp[1] = 1
        dp[2] = 2
        for (i in 3..level) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[level]
    }
}

fun main() {
    val level = 40

    val time2Down = measureTimeMillis {
        val count = PaLouTi().solution2Down(level)
        println("走到第${level}层楼梯一共有${count}种方法")
    }
    println("solution2Down in $time2Down ms")

    val time2Up = measureTimeMillis {
        val count = PaLouTi().solution2Up(level)
        println("走到第${level}层楼梯一共有${count}种方法")
    }
    println("solution2Up in $time2Up ms")
}