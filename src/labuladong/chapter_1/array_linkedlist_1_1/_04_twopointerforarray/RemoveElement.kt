package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。
 * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。

 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
class RemoveElement {

    /**
     * 老规矩，先直觉
     * 1. 如果不考虑什么额外空间，只需要新建一个数组，遍历一遍，就行了
     * 2. 原地，一看到这2字，就下意识的考虑，双指针了
     */
    fun solution(nums: IntArray, `val`: Int): Int {
        var slow = 0
        var fast = 0
        while (fast < nums.size) {
            if (nums[fast] != `val`) {
                nums[slow] = nums[fast]
                slow += 1
            }
            fast += 1
        }
        return slow.also { println("nums=${nums.contentToString()}, size=$it") }
    }
}

fun main() {
    RemoveElement().run {
        (intArrayOf(3, 2, 2, 3) to 3).run {
            solution(first, second)
        }
        (intArrayOf(0, 1, 2, 2, 3, 0, 4, 2) to 2).run {
            solution(first, second)
        }
    }
}