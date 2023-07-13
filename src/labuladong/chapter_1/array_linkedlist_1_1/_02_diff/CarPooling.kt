package labuladong.chapter_1.array_linkedlist_1_1._02_diff

/**
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数 capacity 和一个数组 trips ,
 * trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，
 * 接他们和放他们的位置分别是 fromi 和 toi 。
 * 这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 *
 * 示例 1：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 *
 * 示例 2：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 */
class CarPooling {

    /**
     * capacity：空座位
     * trips：[0]乘客数，[1]上车站，[2]下车站
     *
     * 示例1分析：
     * 1. 4个空座位
     * 2.
     * 2.1 有2个乘客，从第1站上车，第5站下车
     * 2.2 有3个乘客，从第3站上车，第7站下车
     * 这个时候，在第3站的时候，2+3，共有5个乘客，无法满足，所以false
     *
     * 示例2分析：
     * 1. 5个空座位
     * 2.
     * 2.1 有2个乘客，从第1站上车，第5站下车
     * 2.2 有3个乘客，从第3站上车，第7站下车
     * 乘客没有超标，所以true
     *
     * 直觉的思路，就是，
     * 1. 数组[0]就是初始的空座位数，
     * 2. 每一站index+1，上乘客，减座位数，
     * 3. 最后遍历全部数组，只要任何一个值，小于0，就算失败
     */
    fun solution(trips: Array<IntArray>, capacity: Int): Boolean {
        // 构造每一站的空座位数量的数组，题目说了，最大1000站，加上初始空座位数
        val seatsNumOfStation = IntArray(1001)
        seatsNumOfStation[0] = capacity
        // 声明increment函数
        fun increment(value: Int, i: Int, j: Int) {
            seatsNumOfStation[i] -= value
            if (j <= seatsNumOfStation.lastIndex) {
                seatsNumOfStation[j] += value
            }
        }
        // 执行数据更新
        trips.forEach {
            increment(it[0], it[1], it[2])
        }
        // 判断结果
        var seatsNum = 0
        seatsNumOfStation.forEach {
            seatsNum += it
            if (seatsNum < 0) return false
        }
        return true
    }
}

fun main() {
    CarPooling().run {
        solution(
            arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 3, 7)), 4
        ).also { println(it) }
        solution(
            arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 3, 7)), 5
        ).also { println(it) }
        solution(
            arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 5, 7)), 3
        ).also { println(it) }
    }
}