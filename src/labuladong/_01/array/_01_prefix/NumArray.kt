package labuladong._01.array._01_prefix

/**
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
class NumArray(val nums: IntArray) {

    /**
     * 算法1：那就直接加呗
     */
    fun sumRange(left: Int, right: Int): Int {
        var sum = 0
        for (i in left..right) {
            sum += nums[i]
        }
        return sum
    }

    private val sums2: IntArray = IntArray(nums.size) { 0 }

    /**
     * 第一种：初始化的时候，可以提前计算好，所有累加值，之后调用sumRange()直接取就好
     * 第二种：调用sumRange()的时候，先查有没有已经计算好的，有的话直接用，没有的话，再计算，计算后，保存起来
     * 这里，为了方便讲思路，用第一种来演示
     */
    init {
        var sum = 0
        for (i in nums.indices) {
            sum += nums[i]
            sums2[i] = sum
        }
    }

    /**
     * 算法2：避免重复计算，开辟新空间，记录所有累加值
     * PS：根据C++的知识点，每一个if分支，都会导致，计算器内存地址中，来回跳跃，导致损耗性能。
     * 所以如果有要求，且有精力和时间，可以将这个作为优化点，即，算法3
     */
    fun sumRange2(left: Int, right: Int): Int {
        val sum: Int = if (left == 0) {
            sums2[right]
        } else {
            sums2[right] - sums2[left - 1]
        }
        return sum
    }

    /**
     * 多加一个元素，首位为0，累加值下标后移一位
     */
    private val preSum: IntArray = IntArray(nums.size + 1) { 0 }

    init {
        for (i in nums.indices) {
            // preSum[0] = 0 初始化的时候，就定好了
            preSum[i + 1] = preSum[i] + nums[i]
        }
    }

    /**
     * 算法3：由于preSum多一个前置位，所以可以避免if分支
     */
    fun sumRange3(left: Int, right: Int): Int {
        return preSum[right + 1] - preSum[left]
    }
}

fun main() {
    NumArray(intArrayOf(-2, 0, 3, -5, 2, -1)).run {
        println("算法1：")
        println(sumRange(0, 2))
        println(sumRange(2, 5))
        println(sumRange(0, 5))
        println("算法2：")
        println(sumRange2(0, 2))
        println(sumRange2(2, 5))
        println(sumRange2(0, 5))
        println("算法3：")
        println(sumRange3(0, 2))
        println(sumRange3(2, 5))
        println(sumRange3(0, 5))
    }
}