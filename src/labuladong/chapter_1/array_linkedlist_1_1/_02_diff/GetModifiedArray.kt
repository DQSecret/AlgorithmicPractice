package labuladong.chapter_1.array_linkedlist_1_1._02_diff

/**
 * 示例:
 *
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 *
 * 解释:
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 */
class GetModifiedArray {

    /**
     * 还是老规矩，先按照直觉，写算法
     */
    fun solution(length: Int, updates: Array<IntArray>): IntArray {
        val result = IntArray(length)
        updates.forEach {
            for (index in it[0]..it[1]) {
                result[index] += it[2]
            }
        }
        return result
    }

    /**
     * 利用差分思想，将，数组，转化为，关系
     */
    fun solution2(length: Int, updates: Array<IntArray>): IntArray {
        return Difference(length).apply {
            updates.forEach {
                increment(it[0], it[1], it[2])
            }
        }.result()
    }
}

fun main() {

    GetModifiedArray().run {
        solution(
            5, arrayOf(intArrayOf(1, 3, 2), intArrayOf(2, 4, 3), intArrayOf(0, 2, -2))
        ).also {
            println(it.contentToString())
        }
    }

    Difference(5).run {
        increment(1, 3, 2)
        increment(2, 4, 3)
        increment(0, 2, -2)
        println(result().contentToString())
    }
}

class Difference(private val length: Int, private val ori: IntArray = IntArray(length)) {

    private val action: IntArray = IntArray(length)

    init {
        // preAction1()
        preAction2()
    }

    /**
     * 想法1:
     * 先利用，额外的一个数组，来存储关系，之后重新计算返回即可。
     */
    private fun preAction1() {
        action[0] = ori[0]
        for (i in 1..ori.lastIndex) {
            action[i] = ori[i] - ori[i - 1]
        }
    }

    /**
     * 想法2:
     * 直接在修改原数组，之后还原即可。
     */
    private fun preAction2() {
        var last = ori[0]
        for (i in 1..ori.lastIndex) {
            val action = ori[i] - last
            last = ori[i]
            ori[i] = action
        }
    }

    /**
     * 对关系进行操作
     */
    fun increment(i: Int, j: Int, value: Int) {
//        action[i] += value
//        if (j + 1 <= action.lastIndex) {
//            action[j + 1] -= value
//        }
        ori[i] += value
        if (j + 1 <= ori.lastIndex) {
            ori[j + 1] -= value
        }
    }

    /**
     * 根据关系还原
     */
    fun result(): IntArray {
//        for (i in 1..action.lastIndex) {
//            action[i] = action[i - 1] + action[i]
//        }
//        return action
        for (i in 1..ori.lastIndex) {
            ori[i] = ori[i - 1] + ori[i]
        }
        return ori
    }
}