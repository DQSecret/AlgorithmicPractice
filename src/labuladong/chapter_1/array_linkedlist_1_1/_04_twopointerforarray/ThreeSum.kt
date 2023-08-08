package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 升级一下，三数之和，看看怎么解～
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 */
class ThreeSum {

    /**
     * 先试试直觉...
     *
     * 1. 三个for循环，暴力解，没意思...
     * 2. 利用[TwoSum]思想，将问题简化...
     * 2.1. 先排序，
     * 2.2. 然后，将
     */
    fun solution(nums: IntArray): List<List<Int>> {
        // 初始化
        val result = mutableListOf<List<Int>>()
        // 先，排序
        nums.sort()
        // 遍历，扔掉最后2个
        for (i in 0..nums.lastIndex - 2) {
            // 去除掉重复目标值①
            if (i > 0 && nums[i] == nums[i - 1]) continue
            // 利用 TwoSum() 的思路，找到目标值
            // 左右指针
            val target = -nums[i]
            var left = i + 1 // i的下一个
            var right = nums.lastIndex
            // 然后，开始缩小范围
            while (left < right) {
                val sum = nums[left] + nums[right]
                if (sum > target) {
                    right -= 1
                } else if (sum < target) {
                    left += 1
                } else if (sum == target) {
                    // 这里就不能直接返回了，因为不是唯一解
                    // return mutableListOf(nums[left], nums[right])
                    // 需要把结果存下来
                    result.add(mutableListOf(nums[left], nums[right], -target))
                    // 添加完后，继续缩减间隔
                    left += 1
                    right -= 1
                    // 去除掉重复目标值②
                    while (left < right && nums[left] == nums[left - 1]) {
                        left += 1
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right -= 1
                    }
                }
            }
        }
        // 返回结果
        return result
    }
}

fun main() {
    ThreeSum().run {
        solution(intArrayOf(-1, 0, 1, 2, -1, -4)).also { println(it) }
    }
}