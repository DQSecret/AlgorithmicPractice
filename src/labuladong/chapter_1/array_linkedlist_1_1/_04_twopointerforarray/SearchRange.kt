package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 在排序数组中查找元素的第一个和最后一个位置 - [Search]二分查找的进阶
 *
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
 * 请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
class SearchRange {

    /**
     * 在解决这个题目之前，需要先根据[Search]，解决左边界[solutionLeft]，右边界[solutionRight]的问题
     *
     * 此时，就相当于，把左右边界点同时找出来
     *
     * PS：对于最后的边界溢出判断，
     * if (left !in nums.indices) return -1
     * if (right !in nums.indices) return -1
     * 可以前置到，最前面！！！如下：
     * // if (nums.isEmpty()) return -1
     * // if (nums.first() < target) return -1
     * // if (nums.last() > target) return -1
     * 这样更方便一点～
     */
    fun solution(nums: IntArray, target: Int): IntArray {
        val left = solutionLeft(nums, target)
        val right = solutionRight(nums, target)
        return intArrayOf(left, right)
    }

    /**
     * 当二分查找时，目标值，可能不仅一个，当时多个的时候，找到这一串连续目标值的左边第一个的下标
     * eg：nums = [5,7,7,7,7,10], target = 7
     * 返回，1，即，第一个7的下标
     */
    fun solutionLeft(nums: IntArray, target: Int): Int {
        // 0. 左右边界
        var left = 0
        var right = nums.lastIndex
        // 1. 循环条件｜终止条件
        while (left <= right) {
            // 2. mid的判断，更新左右边界
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1
            } else if (nums[mid] < target) {
                // 搜索区间变为[mid+1, right]
                left = mid + 1
            } else if (nums[mid] > target) {
                // 搜索区间变为[left, mid-1]
                right = mid - 1
            }
        }
        // 3. 返回值
        // 3.1 如果整个数组里全都小于target，比如nums=[1,2]，target=3。最后的left=2，会溢出，所以得返回-1
        if (left !in nums.indices) return -1
        // 3.2 如果没找到，比如nums=[4,6]，target=5。最后的left=1，即没有找到target，所以得返回-1
        // 3.3 如果整个数组里全都大于target，比如nums=[5,6]，target=4。最后的left=0，即没有找到target，所以得返回-1
        return if (nums[left] == target) left else -1
    }

    fun solutionRight(nums: IntArray, target: Int): Int {
        // 0. 左右边界
        var left = 0
        var right = nums.lastIndex
        // 1. 循环条件｜终止条件
        while (left <= right) {
            // 2. mid的判断，更新左右边界
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                left = mid + 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid - 1
            }
        }
        // 3. 返回值，考虑3种情况
        // 3.1 nums=[1,2]，target=3，最终right=1，left=2；没有找到，需要返回-1
        // 3.2 nums=[1,3]，target=2，最终right=0，left=1；没有找到，需要返回-1
        // 3.3 nums=[2,3]，target=1，最终right=-1，left=0；越界，需要返回-1
        // 小结，可以看到，终止条件是，left=right+1 ==> right==left-1
        if (right !in nums.indices) return -1
        return if (nums[right] == target) right else -1
    }
}

fun main() {
    SearchRange().run {
        println("solutionLeft")
        println(solutionLeft(intArrayOf(5, 7, 7, 7, 7, 10), 7))
        println(solutionLeft(intArrayOf(4, 5, 7, 7, 7, 7, 10, 11), 7))
        println(solutionLeft(intArrayOf(1, 2), 3))
        println(solutionLeft(intArrayOf(1, 3), 2))
        println(solutionLeft(intArrayOf(2, 3), 1))
        println(solutionLeft(intArrayOf(0), 1))
        println("solutionRight")
        println(solutionRight(intArrayOf(5, 7, 7, 7, 7, 10), 7))
        println(solutionRight(intArrayOf(4, 5, 7, 7, 7, 7, 10, 11), 7))
        println(solutionRight(intArrayOf(1, 2), 3))
        println(solutionRight(intArrayOf(1, 3), 2))
        println(solutionRight(intArrayOf(2, 3), 1))
        println(solutionRight(intArrayOf(), 1))
        println("solution")
        println(solution(intArrayOf(5, 7, 7, 8, 8, 10), 8).contentToString())
        println(solution(intArrayOf(5, 7, 7, 8, 8, 10), 6).contentToString())
        println(solution(intArrayOf(), 0).contentToString())
    }
}