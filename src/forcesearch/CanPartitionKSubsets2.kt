package forcesearch

/**
 * DFS 算法练习题
 *
 * @author DQ For Olivia
 * @since 2021/11/17 4:26 下午
 * @see <a href="https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/">力扣题目</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/105/">讲解</a>
 */
class CanPartitionKSubsets2 {

    /**
     * 视角2:
     * 1.每个桶, 都需要遍历nums中的所有数字, 以判断要不要装入这个数字
     * 2.当装满一个桶之后，还要装下一个桶，直到所有桶都装满为止。
     */
    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        // 排除特殊案例
        if (k <= 0 || k > nums.size || nums.sum() % k != 0) return false
        // 由于第一步已经过滤了,所以必定整除
        val average: Int = nums.sum() / k
        // PS: 额外增加一个判断条件,当有一个元素大于平均值,绝对无法均分
        if (nums.any { it > average }) return false
        // 最后所有的木桶
        val buckets = IntArray(k)
        // 执行回溯 PS: 先降序排列一下,是的裁剪条件,尽量命中
        return backtrack(buckets, 0, nums.apply { sortDescending() }, 0, average)
    }

    /**
     * 把自己当做木桶去审视, 遍历每个数字, 判断要不要装入当前数字
     * @param index: 木桶的下标
     * @param start: 从哪个数字开始遍历 - 优化项
     */
    private fun backtrack(
        buckets: IntArray,
        index: Int,
        nums: IntArray,
        start: Int,
        average: Int
    ): Boolean {
        // 结束条件: index超出木桶个数,则一定全部OK了
        if (index == buckets.size) {
            return true
        }

        // 回溯条件, 装满了当前桶，递归穷举下一个桶的选择
        if (buckets[index] == average) {
            return backtrack(buckets, index + 1, nums, 0, average)
        }

        // 这个开始的下标0,是可以优化的
        for (i in start..nums.lastIndex) {
            // 1. 剪枝
            if (nums[i] == 0) {
                // nums[i] 已经被装入别的桶中
                continue
            }
            if (buckets[index] + nums[i] > average) {
                // 当前桶装不下 nums[i]
                continue
            }

            // 2. 做选择, 将 nums[i] 装入当前桶中
            val temp = nums[i]
            nums[i] = 0
            buckets[index] += temp

            // 3. 递归穷举下一个数字是否装入当前桶
            if (backtrack(buckets, index, nums, i + 1, average)) {
                return true
            }

            // 4. 撤销选择
            buckets[index] -= temp
            nums[i] = temp
        }
        return false
    }
}

fun main() {
    // true
    println(CanPartitionKSubsets2().canPartitionKSubsets(intArrayOf(4, 3, 2, 3, 5, 2, 1), 4))
    // false
    println(CanPartitionKSubsets2().canPartitionKSubsets(intArrayOf(1, 2, 3, 4), 3))
    // false
    println(CanPartitionKSubsets2().canPartitionKSubsets(intArrayOf(2, 2, 2, 2, 3, 4, 5), 4))
}