/**
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
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