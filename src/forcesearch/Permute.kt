package forcesearch

/**
 * DFS 算法练习题
 *
 * 回溯算法团灭排列/组合/子集问题
 *
 * @author DQ For Olivia
 * @since 2021/12/7 5:28 下午
 * @see <a href="https://leetcode-cn.com/problems/permutations/">力扣题目</a>
 * @see <a href="https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA">讲解</a>
 */
class Permute {

    val result: MutableList<List<Int>> = mutableListOf()

    /**
     * 全排列: 给定一个不含重复数字的数组nums,返回其所有可能的全排列.
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：[[1]]
     */
    fun permute(nums: IntArray): List<List<Int>> {
        backtrack(nums, mutableListOf())
        return result
    }

    private fun backtrack(nums: IntArray, permute: MutableList<Int>) {
        if (permute.size == nums.size) {
            result.add(permute.toList())
            return
        }
        for (i in 0..nums.lastIndex) {
            // 裁剪枝叶
            if (permute.contains(nums[i])) continue
            // 做选择
            permute.add(nums[i])
            // 回溯
            backtrack(nums, permute)
            // 撤销选择
            permute.removeLast()
        }
    }
}

fun main() {
    println(Permute().permute(intArrayOf(1)))
    println(Permute().permute(intArrayOf(0, 1)))
    println(Permute().permute(intArrayOf(1, 2, 3)))
}