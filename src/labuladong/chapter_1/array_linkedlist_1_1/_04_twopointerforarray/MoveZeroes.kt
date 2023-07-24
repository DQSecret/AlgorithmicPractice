package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 紧接着[RemoveElement]的延伸练习
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 */
class MoveZeroes {

    /**
     * 老规矩，先直觉
     * 1. 又是原地，上双指针
     */
    fun solution(nums: IntArray) {
        var slow = 0
        var fast = 0
        while (fast < nums.size) {
            if (nums[fast] != 0) {
                // 2个值交换
                nums[slow] = nums[fast].also { nums[fast] = nums[slow] }
                slow += 1
            }
            fast += 1
        }
        println(nums.contentToString())
    }
}

fun main() {
    MoveZeroes().run {
        solution(intArrayOf(0, 1, 0, 3, 12))
        solution(intArrayOf(0))
    }
}