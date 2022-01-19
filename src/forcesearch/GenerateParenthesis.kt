package forcesearch


/**
 * DFS 算法练习题
 *
 * 括号问题可以简单分成两类:
 * 一类: 括号的合法性判断, 主要是借助「栈」这种数据结构
 * 另一类: 合法括号的生成, 一般都要利用回溯递归的思想
 *
 * @author DQ For Olivia
 * @since 2022/1/19 2:18 下午
 * @see <a href="https://leetcode-cn.com/problems/generate-parentheses/">力扣题目</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/112/">讲解</a>
 */
class GenerateParenthesis {

    private val result: MutableList<String> = mutableListOf()

    /**
     * 数字n代表生成括号的对数,设计一个函数,用于能够生成<所有>可能的并且<有效>的括号组合。
     * 示例1:
     *   输入:n = 1
     *   输出:["()"]
     * 示例2:
     *   输入:n = 2
     *   输出:["()()","()()","(())"] - 所有空位插入一个"()"
     *   简化:["()()","(())"]
     * 示例3:
     *   输入:n = 3
     *   输出:["()()()","(())()","()()()","()(())","()()()",
     *        "()(())","(()())","((()))","(()())","(())()"] - 所有空位插入一个"()"
     *   输出:["()()()","(())()","()(())",
     *        "(()())","((()))"]
     *
     * 提示:1 <= n <= 8
     *
     * 思路一:先全排列,然后过滤
     * 思路二:观察发现,后者可以由前者推导出来;每多一对,就是对前面的计算结果的每一项的没一个空位,都多填一对(),可以优化
     */
    fun generateParenthesis(n: Int): List<String> {
        result.clear()
        backtrack(n, StringBuilder(n))
        return result.filter { it.isValid() }
    }

    /**
     * 解题套路:自己想的
     */
    private fun backtrack(n: Int, sb: StringBuilder) {
        if (n * 2 == sb.length) {
            result.add(sb.toString())
            return
        }
        // 做出选择
        for (choice in arrayOf('(', ')')) {
            sb.append(choice)
            // 回溯
            backtrack(n, sb)
            // 撤销选择
            sb.deleteCharAt(sb.lastIndex)
        }
    }

    fun String.isValid(): Boolean {
        if (this.first() != '(') return false
        if (this.last() != ')') return false
        val stack = mutableListOf<Char>()
        this.forEach { c ->
            if (stack.isEmpty()) {
                stack.add(c)
            } else {
                if (stack.last() == '(' && c == ')') {
                    stack.removeAt(stack.lastIndex)
                } else {
                    stack.add(c)
                }
            }
        }
        return stack.isEmpty()
    }

    /**
     * 解题套路:东哥给的
     * 1、一个「合法」括号组合的左括号数量一定等于右括号数量，这个很好理解。
     * 2、对于一个「合法」的括号字符串组合 p，必然对于任何 0 <= i < len(p) 都有：子串 p[0..i] 中左括号的数量都大于或等于右括号的数量。
     */
    fun generateParenthesis2(n: Int): List<String> {
        if (n == 0) return listOf()
        // 记录所有合法的括号组合
        val res: MutableList<String> = mutableListOf()
        // 回溯过程中的路径
        val track: StringBuilder = StringBuilder()
        // 可用的左括号和右括号数量初始化为 n
        backtrack2(n, n, track, res)
        return res.toList()
    }

    /**
     * @param left:     可用的 ( 数量
     * @param right:    可用的 ) 数量
     */
    private fun backtrack2(left: Int, right: Int, track: StringBuilder, res: MutableList<String>) {
        if (left > right) return
        if (left < 0 || right < 0) return
        if (left == 0 && right == 0) {
            res.add(track.toString())
            return
        }
        // 做选择
        track.append('(')
        // 回溯
        backtrack2(left - 1, right, track, res)
        // 撤销选择
        track.deleteCharAt(track.lastIndex)

        // 做选择
        track.append(')')
        // 回溯
        backtrack2(left, right - 1, track, res)
        // 撤销选择
        track.deleteCharAt(track.lastIndex)
    }
}

fun main() {
    GenerateParenthesis().run {
        // 测试:检测是否有效
        listOf(")(", "((()))", "(()())", "(())()", "()(())", "()()()")
            .joinToString("\n", transform = { "[ $it ] = ${it.isValid()}" })
            .also { println(it) }
        // 测试:构建全排列并过滤
        println(generateParenthesis2(3))
        println(generateParenthesis(3))
    }
}