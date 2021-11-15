package adventofcode.y2015.d8

import adventofcode.base.Day
import adventofcode.util.Runner
import kotlin.time.ExperimentalTime

/**
 * 看完题目,应该挺简单的,过滤掉 \\ \" \x 等特殊转义, 剩下的相减就可以了
 *
 * @author DQ For Olivia
 * @since 2021/11/11 7:57 下午
 * @see <a href="https://adventofcode.com/2015/day/8">文章</a>
 */
class Matchsticks : Day(2015, 8) {

    companion object {

        const val escaped = "\\\\"

        const val quote = "\\\""

        const val hexPre = "\\x"

        const val hexadecimal = "0123456789abcdef"
    }

    /**
     * 是否为 16 进制符号
     */
    private fun isHexadecimal(code: String): Boolean {
        return code.startsWith(hexPre)
                && hexadecimal.contains(code[code.lastIndex - 1])
                && hexadecimal.contains(code.last())
    }

    /**
     * 是否为 引号
     */
    private fun isQuote(code: String): Boolean {
        return code == quote
    }

    /**
     * 是否为 转义字符
     */
    private fun isEscaped(code: String): Boolean {
        return code == escaped
    }

    private fun evaluate(str: String): Int {
        val total = str.length
        var valid = 0

        // 跳过首尾2个引号
        var i = 1
        while (i < str.lastIndex) {
            val curr = str[i]
            if (curr == '\\') {
                when {
                    isEscaped(str.substring(i, i + 2)) -> { // 判断 转义
                        valid += 1
                        i += 2
                    }
                    isQuote(str.substring(i, i + 2)) -> { // 判断 引号
                        valid += 1
                        i += 2
                    }
                    isHexadecimal(str.substring(i, i + 4)) -> { // 判断 16 进制
                        valid += 1
                        i += 4
                    }
                }
            } else {
                valid += 1
                i += 1
            }
        }
        return total - valid
    }

    /**
     * 在一的基础上,特殊字符个数+2,即可
     */
    private fun evaluate2(str: String): Int {
        var expand = 2 // 所有扩展操作完成之后,需要在首尾新增两个"
        expand += str.count {
            it == '\\' || it == '\"'
        }
        return expand
    }

    override fun partOne(): Any {
        return inputList
            .sumOf { evaluate(it) }
            .let { "space:{$it}" }
    }

    override fun partTwo(): Any {
        return inputList
            .sumOf { evaluate2(it) }
            .let { "space:{$it}" }
    }
}

@ExperimentalTime
fun main() {
    Runner.printDay(Matchsticks())
}