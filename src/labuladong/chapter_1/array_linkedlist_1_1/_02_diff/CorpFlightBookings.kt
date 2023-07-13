package labuladong.chapter_1.array_linkedlist_1_1._02_diff

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 *
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 *
 * 示例 1：
 *
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer =[10, 55, 45, 25, 25]
 */
class CorpFlightBookings {

    fun solution(bookings: Array<IntArray>, n: Int): IntArray {
        // 构建原数据
        val res = IntArray(n)
        // 构建diff
        val diff = IntArray(n)
        diff[0] = res[0]
        for (i in 1..res.lastIndex) {
            diff[i] = res[i] - res[i - 1]
        }
        // 声明increment函数
        fun increment(i: Int, j: Int, value: Int) {
            diff[i] += value
            if (j + 1 <= diff.lastIndex) {
                diff[j + 1] -= value
            }
        }
        // 执行数据更新
        bookings.forEach {
            // 这里之所以要-1，是因为题目中输入时，不是从0开始的
            increment(it[0] - 1, it[1] - 1, it[2])
        }
        // 构建result
        val result = IntArray(n)
        result[0] = diff[0]
        for (i in 1..result.lastIndex) {
            result[i] = result[i - 1] + diff[i]
        }
        return result
    }
}

fun main() {
    CorpFlightBookings().run {
        solution(
            arrayOf(
                intArrayOf(1, 2, 10),
                intArrayOf(2, 3, 20),
                intArrayOf(2, 5, 25),
            ), 5
        ).also {
            println(it.contentToString())
        }
    }
}