package tencentselectpractice.medium

import kotlin.math.max
import kotlin.math.min

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai)
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
 * 说明：你不能倾斜容器，且 n 的值至少为 2
 *
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
class MaxArea {

    /**
     * 暴力法
     */
    fun solution1(arr: IntArray): Int {
        var area = 0
        for (i in 0 until arr.lastIndex) {
            for (j in 1 until arr.size) {
                val width = j - i
                val height = min(arr[i], arr[j])
                area = max(area, width * height)
            }
        }
        return area
    }

    /**
     * 双指针, 从两侧向中间逼近
     */
    fun solution2(arr: IntArray): Int {
        var area = 0
        var left = 0
        var right = arr.lastIndex
        while (left < right) {
            area = max(area, (right - left) * min(arr[left], arr[right]))
            if (arr[left] < arr[right]) {
                left += 1
            } else {
                right -= 1
            }
        }
        return area
    }
}

fun main() {
    testMaxArea()
}

fun testMaxArea() {
    MaxArea().run {
        val arr = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
        println(solution1(arr))
        println(solution2(arr))
    }
}