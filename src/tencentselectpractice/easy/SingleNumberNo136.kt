package tencentselectpractice.easy

import kotlin.system.measureTimeMillis

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
class SingleNumber {

    /**
     * 方法 1: 两层for 时间:O(n2) 空间 O(n)
     * 方法 2: 先排序,再for,和前一个元素对比,相等减掉,不等加上, 时间:O(log(n)·n)
     * 方法 3: 一层for,一个 map 时间:O(n·1) 空间 O(n/2+1)≈O(n)
     * 方法 4: 数学计算 2*(a+b+c)−(a+a+b+b+c)=c 利用 set 去除重复数据
     *         时间O(n+n)=O(n) 空间O(n+n)=O(n)
     * 方法 5: 异或: a^a=0 0^b=b 故 a^b^a = a^a^b = b
     */
    fun solution(nums: IntArray): Int {
        var result = 0
        for (num in nums) {
            result = result xor num
        }
        return result
    }
}

fun main() {
    val nums = intArrayOf(4, 1, 2, 1, 2)
    val time = measureTimeMillis {
        val single = SingleNumber().solution(nums)
        println("只出现了一次的元素为: $single")
    }
    println("solution in $time ms")
}