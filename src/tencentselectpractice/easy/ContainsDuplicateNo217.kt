package tencentselectpractice.easy

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true

 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false

 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
class ContainsDuplicate {

    /**
     * 1, 暴力循环 O(n2)
     * 2, hash O(n)
     * 3, 先排序O(n·logn), 再遍历 O(n)
     */
    fun solution(nums: IntArray): Boolean {
        nums.sort()
        for (i in 0 until nums.lastIndex) {
            if (nums[i] == nums[i + 1]) return true
        }
        return false
    }
}

fun main() {
    testContainsDuplicate()
}

fun testContainsDuplicate() {
    val arr = intArrayOf(1, 2, 3, 1)
    println(ContainsDuplicate().solution(arr))
}