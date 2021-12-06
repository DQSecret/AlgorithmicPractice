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
        return backtrack(buckets, 0, nums.apply { sortDescending() }, average)
    }

    /**
     * @param index: 木桶的下标
     */
    private fun backtrack(buckets: IntArray, index: Int, nums: IntArray, average: Int): Boolean {
        // 结束条件: index 超出 木桶个数
        if (index == buckets.size) {
            // 判断每个木桶中的总和是否为平均数
            return buckets.all { it == average }
        }
        // 当前木桶,遍历数字,以判断要不要装入这个数字
        for (i in 0..nums.lastIndex) {
            val temp = nums[i]
            // 裁剪枝叶: 如果桶内数字和超过平均值,则下一个
            if (buckets[index] + temp > average) continue
            // 裁剪枝叶: 如果是0,则代表已经是用过了
            if (temp == 0) continue
            // 做出选择
            buckets[index] += temp
            nums[i] = 0
            // 如果当前桶满了,则下一个桶
            if (buckets[index] == average) {
                return backtrack(buckets, index + 1, nums, average)
            } else {
                if (backtrack(buckets, index, nums, average)) {
                    return true
                } else {
                    // 撤销选择
                    buckets[index] -= temp
                    nums[i] = temp
                }
            }
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