package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

import kotlin.system.measureNanoTime

/**
 * 给你一个 升序排列 的数组 nums ，
 * 请你 **原地** 删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长度。
 * 元素的 **相对顺序** 应该保持 **一致** 。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。

 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 */
class RemoveDuplicates {

    /**
     * 老规矩，先按直觉来尝试
     * 题目中，要求是，原地！！！也就是说，要在，原数组上，直接修改？
     */
    fun solution(nums: IntArray): Int {
        var i = 0 // 遍历的下标，用于定位
        var j = 1 // 嗅探的下标，用于判断后面的元素，是否重复
        while (true) {
            // 下一个，与当前值相同，
            // 这里用>=判断，是为了防止，后面的元素比前面的小，被重新计算了
            // 因为题目中说，是升序的，所以，后面的值，一旦小于等于当前值，就肯定是重复的
            // TIPS：这里，这里突然灵感一动，这里存在重复判断，可以优化～
            if (nums[i] >= nums[j]) {
                j += 1
            } else { // 不同的话，更新值
                nums[i + 1] = nums[j]
                i += 1
                j = i + 1
            }
            // 到最后一个了，结束
            if (j > nums.lastIndex) break
        }
        // 返回下标+1，即是元素个数
        return (i + 1).also { println("修改后的数组是：${nums.contentToString()}，元素总数为：$it") }
    }

    /**
     * 针对[solution]的优化
     */
    fun solution2(nums: IntArray): Int {
        // 前置判断
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return 1
        // 双指针
        var i = 0
        var j = 1
        // 开始遍历
        while (j < nums.size) {
            // 值不同，即不重复
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j]
                i += 1
            } else { // 值相同，即重复
                j += 1
            }
        }
        // 返回下标+1，即是元素个数
        return (i + 1).also { println("修改后的数组是：${nums.contentToString()}，元素总数为：$it") }
    }
}

fun main() {
    RemoveDuplicates().run {
        with(intArrayOf(1, 1, 2)) {
            measureNanoTime { solution(this) }.also { println("耗时：${it}ns.") }
            measureNanoTime { solution2(this) }.also { println("耗时：${it}ns.") }
        }
        with(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)) {
            measureNanoTime { solution(this) }.also { println("耗时：${it}ns.") }
            measureNanoTime { solution2(this) }.also { println("耗时：${it}ns.") }
        }
    }
}