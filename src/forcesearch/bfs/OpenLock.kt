package forcesearch.bfs

import java.util.*

/**
 * DFS 算法练习题
 *
 * 打开转盘锁
 *
 * @author DQ For Olivia
 * @since 2022/1/25 4:53 下午
 * @see <a href="https://leetcode-cn.com/problems/open-the-lock/">力扣题目</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/113/">讲解</a>
 *
 */
class OpenLock {

    fun openLock(deadEnds: Array<String>, target: String): Int {
        val password = "0000"
        val queue = ArrayDeque<String>()
        val visited = mutableSetOf<String>()

        var step: Int = -1
        if (password in deadEnds) return step

        queue.addLast(password)
        visited.add(password)
        step += 1
        if (password == target) return step

        all@ while (queue.isNotEmpty()) {
            it@ for (index in 0 until queue.size) {
                val curr = queue.removeFirst()
                if (curr == password) {
                    break@all
                } else {
                    (getNeighbors(curr) - deadEnds).forEach {
                        TODO("这里没写完,时间隔得有点久,忘记了思路了,后续重新学习BFS的时候,补上.")
                    }
                }
            }
        }

        return step
    }

    private fun getNeighbors(password: String): MutableSet<String> {
        val neighbors = mutableSetOf<String>()
        for (one in 0..getPreviousAndNext(password[0].digitToInt()).lastIndex) {
            for (two in 0..getPreviousAndNext(password[0].digitToInt()).lastIndex) {
                for (three in 0..getPreviousAndNext(password[0].digitToInt()).lastIndex) {
                    for (four in 0..getPreviousAndNext(password[0].digitToInt()).lastIndex) {
                        neighbors.add("$one$two$three$four")
                    }
                }
            }
        }
        return neighbors
    }

    private fun getPreviousAndNext(curr: Int): IntArray {
        return when (curr) {
            0 -> {
                intArrayOf(1, 9)
            }
            9 -> {
                intArrayOf(0, 8)
            }
            else -> {
                intArrayOf(curr - 1, curr + 1)
            }
        }
    }
}

fun main() {

}
