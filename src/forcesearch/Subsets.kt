package forcesearch

/**
 * DFS 算法练习题
 *
 * 回溯算法团灭排列/组合/子集问题
 *
 * @author DQ For Olivia
 * @since 2021/12/6 4:11 下午
 * @see <a href="https://leetcode-cn.com/problems/subsets/">力扣题目</a>
 * @see <a href="https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA">讲解</a>
 */
class Subsets {

    val result: MutableList<List<Int>> = mutableListOf()

    /**
     * 子集问题: 输入一个不包含重复数字的数组，要求算法输出这些数字的所有子集。
     *
     * input:   nums = [1,2,3]
     * desc:    你的算法应输出 8 个子集，包含空集和本身，顺序可以不同：
     * output:  [ [],[1],[2],[3],[1,3],[2,3],[1,2],[1,2,3] ]
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        backtrack(nums, 0, mutableListOf())
        return result
    }

    private fun backtrack(nums: IntArray, index: Int, sets: MutableList<Int>) {
        result.add(sets.toList())
        for (i in index..nums.lastIndex) {
            sets.add(nums[i])
            backtrack(nums, i + 1, sets)
            sets.removeLast()
        }
    }
}

fun main() {
    println(Subsets().subsets(intArrayOf(1, 2)))
    println(Subsets().subsets(intArrayOf(1, 2, 3)))
}