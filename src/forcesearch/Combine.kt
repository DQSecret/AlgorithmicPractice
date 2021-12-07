package forcesearch

/**
 * DFS 算法练习题
 *
 * 回溯算法团灭排列/组合/子集问题
 *
 * @author DQ For Olivia
 * @since 2021/12/6 4:11 下午
 * @see <a href="https://leetcode-cn.com/problems/combinations/">力扣题目</a>
 * @see <a href="https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA">讲解</a>
 */
class Combine {

    val result: MutableList<List<Int>> = mutableListOf()

    /**
     * 组合问题: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *  [1,2], [1,3], [1,4],
     *  [2,3], [2,4],
     *  [3,4]
     * ]
     *
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：
     * [
     *  [1]
     * ]
     */
    fun combine(n: Int, k: Int): List<List<Int>> {
        backtrack(1, n, k, mutableListOf())
        return result
    }

    private fun backtrack(start: Int, end: Int, size: Int, combine: MutableList<Int>) {
        if (combine.size == size) {
            result.add(combine.toList())
            return
        }
        for (i in start..end) {
            combine.add(i)
            backtrack(i + 1, end, size, combine)
            combine.removeLast()
        }
    }
}

fun main() {
    println(Combine().combine(4, 2))
    println(Combine().combine(1, 1))
}