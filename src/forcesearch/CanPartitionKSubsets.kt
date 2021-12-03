package forcesearch

/**
 * DFS 算法练习题
 *
 * @author DQ For Olivia
 * @since 2021/11/17 4:26 下午
 * @see <a href="https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/">力扣题目</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/105/">讲解</a>
 */
class CanPartitionKSubsets {

    /**
     * 示例 1：
     *
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     */
    fun solution(nums: IntArray): Boolean {
        val sum = nums.sum()
        // 1. 确定 K 的范围
        val ks = (1..nums.size).filter { sum % it == 0 }
        // 2. 遍历
        for (k in ks) {
            val result = canPartitionKSubsets(nums, k)
            if (result) {
                return true
            }
        }
        return false
    }

    /**
     * 问题简化为,k为定数,判断可否平均
     *
     * 遍历每一个数字,判断是否应该进入该桶
     */
    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        // 排除特殊案例
        if (k <= 0 || k > nums.size || nums.sum() % k != 0) return false
        // 由于第一步已经过滤了,所以必定整除
        val average: Int = nums.sum() / k
        // 最后所有的木桶
        val buckets = IntArray(k)
        // 执行回溯 PS: 先降序排列一下,是的裁剪条件,尽量命中
        return backtrack(nums.apply { sortDescending() }, 0, buckets, average)
    }

    /**
     * 回溯算法框架:
     *
     * 视角: 将所有数字放进木桶中
     * result = []
     * def backtrack(路径, 选择列表):
     *     if 满足结束条件:
     *         result.add(路径)
     *         return
     *
     *     for 选择 in 选择列表:
     *         做选择
     *         backtrack(路径, 选择列表)
     *         撤销选择
     */
    private fun backtrack(nums: IntArray, index: Int, buckets: IntArray, average: Int): Boolean {
        // 结束条件
        if (index == nums.size) {
            // 判断每个木桶中的总和是否为平均数
            return buckets.all { it == average }
        }
        // 选择逻辑, 将当前这个数字, 放到哪个木桶中?
        for (i in 0..buckets.lastIndex) {
            // 裁剪枝叶: 不放入这个桶
            if (buckets[i] + nums[index] > average) continue
            // 做出选择
            buckets[i] += nums[index]
            if (backtrack(nums, index + 1, buckets, average)) {
                return true
            }
            // 撤销选择
            buckets[i] -= nums[index]
        }
        return false
    }
}

fun main() {
    // true
    println(CanPartitionKSubsets().canPartitionKSubsets(intArrayOf(4, 3, 2, 3, 5, 2, 1), 4))
    // false
    println(CanPartitionKSubsets().canPartitionKSubsets(intArrayOf(1, 2, 3, 4), 3))
    // false
    println(CanPartitionKSubsets().canPartitionKSubsets(intArrayOf(2, 2, 2, 2, 3, 4, 5), 4))
}