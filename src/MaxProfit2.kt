import kotlin.system.measureTimeMillis

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释:
 *      在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释:
 *      在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
class MaxProfit2 {

    fun solution(prices: IntArray): Int {
        if (prices.isEmpty()) return 0
        var count = 0 // 累计收益
        var profit = 0 // 单次收益
        var buy = prices[0] // 买入
        prices.forEach {
            val temp = it - buy
            if (temp < profit) { // 今天卖,比昨天卖赚的少,则昨天卖出,今天买入,重置收益
                count += profit
                buy = it
                profit = 0
            } else // 今天卖,比昨天卖赚的多or一样,继续持有,更新收益
                profit = temp
        }
        count += profit // 如果是一路连涨,最后一天卖
        return count
    }

    /**
     * 这个是真的机智,只要比前一个大,就累加
     */
    fun solution2(prices: IntArray): Int {
        var count = 0
        for (i in 1 until prices.size) {
            if (prices[i - 1] < prices[i]) {
                count += prices[i] - prices[i - 1]
            }
        }
        return count
    }
}

fun main() {
    // val arr = intArrayOf(7, 1, 5, 3, 6, 4)
    // val arr = intArrayOf(1, 2, 3, 4, 5)
    val arr = intArrayOf(7, 6, 4, 3, 1)
    val time = measureTimeMillis {
        val profit = MaxProfit2().solution2(arr)
        println("${arr.contentToString()} 的最大利润是 $profit")
    }
    println("solution in $time ms")
}