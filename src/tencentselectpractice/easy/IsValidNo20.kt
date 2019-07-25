package tencentselectpractice.easy

import java.util.*
import kotlin.system.measureTimeMillis

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
class IsValid {

    // 配对值
    private val map = mapOf(')' to '(', ']' to '[', '}' to '{')

    /**
     * 利用 Stack 处理
     * 需要全部遍历完,才知道结果
     */
    fun solution(str: String): Boolean {
        // 1, 准备参数
        val stack = Stack<Char>()
        // 2, 遍历
        str.trim().forEach {
            if (stack.empty()) {
                stack.push(it)
            } else {
                val top = stack.peek()
                if (top == map[it]) {
                    stack.pop()
                } else {
                    stack.push(it)
                }
            }
        }
        // 3, 返回结果
        return stack.empty()
    }

    /**
     * 利用 ArrayDeque 处理
     * 利用一些条件,提前结束遍历,省时间
     */
    fun solution2(s: String): Boolean {
        if (s.isBlank()) return true
        val queue = ArrayDeque<Char>()
        s.forEach {
            when (it) {
                '(', '[', '{' -> queue.push(it)
                ')', ']', '}' -> {
                    if (queue.isEmpty() // 为空,意味着")",直接错误
                            || queue.pop() != map[it] // 不配对,意味着"(]",直接错误
                    ) {
                        return false
                    }
                }
                else -> return false
            }
        }
        return queue.isEmpty()
    }
}

fun main() {
    val s = "()"
    val s2 = "()[]{}"
    val s3 = "(]"
    val s4 = "([)]"
    val s5 = "{[]}"
    val s6 = ""
    val s7 = " "
    val s8 = "([)"
    val time = measureTimeMillis {
        println(IsValid().solution2(s))
        println(IsValid().solution2(s2))
        println(IsValid().solution2(s3))
        println(IsValid().solution2(s4))
        println(IsValid().solution2(s5))
        println(IsValid().solution2(s6))
        println(IsValid().solution2(s7))
        println(IsValid().solution2(s8))
    }
    println("solution in $time ms")
}