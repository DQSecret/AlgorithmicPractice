import kotlin.system.measureTimeMillis

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
class MaxSubArray {

    /**
     * 动态规划法, 一次遍历
     * 思路:
     *  1)如果当前的和为正,则加上下一个元素,再取最大值
     *  2)如果当前的和为负,则直接和下一个元素对比,取最大值
     *
     * ps: 如果还需要返回下标,则需要多做一个记录
     *
     * 更加暴力的方法
     *  两层循环,以每一个元素为头,向后遍历一遍,计算最大值,时间复杂度 O(n^2),空间复杂度O(1)
     */
    fun solution(nums: IntArray): Int {
        var max = nums[0] // 最大值
        var temp = 0 // 临时和
        for (value in nums) {
            if (temp >= 0) {
                temp += value
            } else {
                temp = value
            }
            max = Math.max(max, temp)
        }
        return max
    }

    fun solutionWithIndex(nums: IntArray): MaxSubArrayResult {
        // 边界处理
        if (nums.isEmpty()) return MaxSubArrayResult(0, 0, 0)
        if (nums.size == 1) return MaxSubArrayResult(nums[0], 0, 0)
        // 临时和 && 最大值
        var temp = Int.MIN_VALUE
        var max = temp
        var start = 0
        var end = 0
        for ((index, value) in nums.withIndex()) {
            if (temp >= 0) {
                temp += value
                if (max <= temp) { // 记录结束坐标
                    end = index
                }
            } else {
                temp = value
                start = index // 记录开始坐标
            }
            max = Math.max(max, temp)
        }
        return MaxSubArrayResult(max, start, end)
    }

    data class MaxSubArrayResult(
            val sum: Int,
            val start: Int,
            val end: Int
    )

    /**
     * 分治法
     * 思路:
     *  1)元素个数为1,则直接返回
     *  2)元素个数为2,则左(0)   - 中(0)   - 右(1)分别计算
     *  3)元素个数为3,则左(0-1) - 中(0-1) - 右(1-2)中右分别计算
     *  4)元素个数为4,则拆分...
     */
    fun solution2(nums: IntArray): Int {
        if (nums.size == 1) {
            return nums[0]
        } else {
            // 中间下标
            val midIndex = nums.lastIndex / 2

            // 递归左边最大
            val rangeLeft = IntRange(0, midIndex)
            val subArrLeft = nums.slice(rangeLeft).toIntArray()
            val maxLeft = solution2(subArrLeft)

            // 递归右边最大
            val rangeRight = IntRange(midIndex + 1, nums.lastIndex)
            val subArrRight = nums.slice(rangeRight).toIntArray()
            val maxRight = solution2(subArrRight)

            // 算中间左
            var sunTemp = 0
            var subLMax = Int.MIN_VALUE
            for (i in midIndex downTo 0) {
                sunTemp += nums[i]
                subLMax = Math.max(subLMax, sunTemp)
            }
            // 算中间右
            sunTemp = 0
            var subRMax = Int.MIN_VALUE
            for (i in (midIndex + 1)..nums.lastIndex) {
                sunTemp += nums[i]
                subRMax = Math.max(subRMax, sunTemp)
            }
            // 求和
            val maxMid = subLMax + subRMax

            val maxLR = Math.max(maxLeft, maxRight)
            return Math.max(maxLR, maxMid)
        }
    }
}

fun main() {
    val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    // 算法
    val time = measureTimeMillis {
        val result = MaxSubArray().solution(nums)
        println("动态规划计算 - 最大子数组和: $result")
    }
    println("solution in $time ms")
    // 显示
    MaxSubArray().solutionWithIndex(nums).run {
        println("最大和的连续子数组为: ${nums.slice(start..end)}, 其和为: $sum\n")
    }
    // 算法
    val time2 = measureTimeMillis {
        val result = MaxSubArray().solution2(nums)
        println("分治法 - 最大子数组和: $result")
    }
    println("solution in $time2 ms")
}