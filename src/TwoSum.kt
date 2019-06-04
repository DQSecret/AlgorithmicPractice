/**
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 难度：简单
 * tips：利用 map.contains() 提高效率
 */
class TwoSum {

    fun solution(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for ((index, item) in nums.withIndex()) {
            if (map.contains(item)) {
                return intArrayOf(map[item]!!, index)
            }
            map[target - item] = index
        }
        return intArrayOf()
    }
}

fun main() {
    val nums = intArrayOf(1, 3, 4, 2)
    val target = 6
    val result = TwoSum().solution(nums, target)
    println(result.contentToString())
}