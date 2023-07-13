package labuladong.chapter_1.array_linkedlist_1_1._01_prefix

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
class ProductExceptSelf {

    /**
     * 还是老规矩，先，按照直觉，写一个
     */
    fun solution(nums: IntArray): IntArray {
        val answer = IntArray(nums.size)
        for (i in nums.indices) {
            var product = 1
            for (j in nums.indices) {
                if (j != i) {
                    product *= nums[j]
                }
            }
            answer[i] = product
        }
        return answer
    }

    /**
     * 上一个算法，时间复杂度是O(n^2)，看看怎么简化
     *
     * 1.
     * 很容易想到，利用除法，提前先把所有的乘积求出来，然后，需要哪一个，就除哪一个，就完了
     * 但是题目要求，不要使用除法
     *
     * 2.
     * 前缀和，怎么做呢？
     * 分为2部分，前缀积 * 后缀积 = 除当前值外，的所有元素的积
     */
    fun solution2(nums: IntArray): IntArray {
        // 由于要求积，所以初始化不能是0，
        val preLeft = IntArray(nums.size) { 1 }
        val preRight = IntArray(nums.size) { 1 }
        // 左积，第一位，是1
        for (i in 1..preLeft.lastIndex) {
            preLeft[i] = nums[i - 1] * preLeft[i - 1]
        }
        // 右积，最后一位，是1
        for (i in preRight.lastIndex - 1 downTo 0) {
            preRight[i] = nums[i + 1] * preRight[i + 1]
        }

        // 求，具体某一个值的时候，就是，左积*右积
        val answer = IntArray(nums.size)
        for (i in nums.indices) {
            answer[i] = preLeft[i] * preRight[i]
        }
        return answer
    }

    /**
     * 上一个算法，借助2个数组，来存储指，用了额外的空间，
     * 看看能不能把空间复杂度，压缩下来
     *
     * 这个算法有点不好理解，需要明确以下几个点：
     * 1. 利用 prefix、suffix 两个变量，单独存储前后缀
     * 2. 每次循环，优先更新值，后更新前后缀；即，i=0时，前缀积为1；i=lastIndex时，后缀积为1
     * 3. 每次循环，后更新前后缀，因为本次计算的前后缀，是要用在下一次的循环中，用于赋值的
     * 4. 当循环，进入后半段时，answer[i]已经被赋值了，存储的是，后缀积，
     *    4.1. 次数，answer[i] * prefix = 就等于目标值了...
     *
     * 很巧妙，相当于，同时，在计算前后缀的值。
     */
    fun solution3(nums: IntArray): IntArray {
        val answer = IntArray(nums.size) { 1 }
        var prefix = 1
        var suffix = 1
        for (i in answer.indices) {
            // 更新值
            answer[i] *= prefix
            answer[answer.lastIndex - i] *= suffix
            // 更新前后缀
            prefix *= nums[i]
            suffix *= nums[answer.lastIndex - i]
        }
        return answer
    }
}

fun main() {
    ProductExceptSelf().apply {
        // 算法1
        println(solution(intArrayOf(1, 2, 3, 4)).contentToString())
        println(solution(intArrayOf(-1, 1, 0, -3, 3)).contentToString())
        // 算法2
        println(solution2(intArrayOf(1, 2, 3, 4)).contentToString())
        println(solution2(intArrayOf(-1, 1, 0, -3, 3)).contentToString())
        // 算法3
        println(solution3(intArrayOf(1, 2, 3, 4)).contentToString())
        println(solution3(intArrayOf(-1, 1, 0, -3, 3)).contentToString())
    }
}