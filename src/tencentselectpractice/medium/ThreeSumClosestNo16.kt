package tencentselectpractice.medium

import kotlin.math.abs

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
class ThreeSumClosest {

    /**
     * 暴力法, 会有三次循环-O(n^3), 放弃
     * 先排序O(n·logn) + 再双指针判断O(n·n)
     */
    fun solution(nums: IntArray, target: Int): Int {
        nums.sort() // O(n·logn)
        var sum = nums[0] + nums[1] + nums[2]
        for (i in 0..nums.lastIndex - 2) { // O(n)
            var j = i + 1
            var k = nums.lastIndex
            while (j < k) { // O(n)
                val tempSum = nums[i] + nums[j] + nums[k]
                if (abs(target - tempSum) < abs(target - sum)) sum = tempSum
                when {
                    tempSum < target -> j += 1
                    tempSum > target -> k -= 1
                    else -> return sum
                }
            }
        }
        return sum
    }
}

fun main() {
    testThreeSumClosest()
}

fun testThreeSumClosest() {
    ThreeSumClosest().also {
        var nums = intArrayOf(-1, 2, 1, -4)
        var target = 1
        println(it.solution(nums, target))
        nums = intArrayOf(0, 2, 1, -3)
        target = 1
        println(it.solution(nums, target))
        nums = intArrayOf(1, 2, 4, 8, 16, 32, 64, 128)
        target = 82
        println(it.solution(nums, target))
    }
}
