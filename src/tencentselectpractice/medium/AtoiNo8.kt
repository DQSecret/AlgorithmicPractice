package tencentselectpractice.medium

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31, 2^31−1]。
 * 如果数值超过这个范围，请返回 INT_MAX(2^31−1) 或 INT_MIN(−2^31) 。
 *
 * 示例 1:
 * 输入: "42"
 * 输出: 42

 * 示例 2:
 * 输入: "-42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42。

 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。

 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 -91283472332 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−2^31)。
 */
class Atoi {

    fun solution(str: String): Int {
        val trim = str.trim()
        if (trim.isBlank()) return 0

        val sb = StringBuilder()
        val first = trim.first()
        if (first == '+' || first == '-' || first in '0'..'9') {
            sb.append(first)
            for (i in 1..trim.lastIndex) {
                val c = trim[i]
                if (c in '0'..'9') {
                    sb.append(c)
                } else {
                    break
                }
            }
        }
        val result = sb.toString()
        if (result.isEmpty() || result == "+" || result == "-") return 0
        return try {
            result.toInt()
        } catch (e: Exception) {
            if (first == '-') Int.MIN_VALUE else Int.MAX_VALUE
        }
    }

    fun solution2(str: String): Int {
        var base = 0
        var sign = 1 // 符号
        var signed = false
        str.trim().forEach {
            if (it == '-') {
                if (signed) return base
                sign = -1
                signed = true
            }
            if (it == '+') {
                if (signed) return base
                signed = true
            }
            if (it.isDigit()) {
                signed = true
                base = appendDigit(it, base * sign, sign)
            }
            if (it != '-' && it != '+' && !it.isDigit()) {
                return base
            }
        }
        return base
    }

    /**
     * TODO("判断边界: 这里不是很理解, 回头再看")
     */
    private fun appendDigit(char: Char, base: Int, sign: Int): Int {
        val digit = char - '0' // wtf?

        if (base * sign > Int.MAX_VALUE / 10
                || base * sign == Int.MAX_VALUE / 10
                && Int.MAX_VALUE - base * sign * 10 < digit
        ) return Int.MAX_VALUE

        if (base * sign < Int.MIN_VALUE / 10
                || base * sign == Int.MIN_VALUE / 10
                && Int.MIN_VALUE - base * sign * 10 > -digit
        ) return Int.MIN_VALUE

        return base * 10 * sign + digit * sign
    }
}

fun main() {
    testAtoi()
}

fun testAtoi() {
    var str = "42"
    println(Atoi().solution(str))
    str = "-42"
    println(Atoi().solution(str))
    str = "4193 with words"
    println(Atoi().solution(str))
    str = "words and 987"
    println(Atoi().solution(str))
    str = "-91283472332"
    println(Atoi().solution(str))
    str = "   -42"
    println(Atoi().solution(str))
    str = "   -42  34"
    println(Atoi().solution(str))
    str = "+1"
    println(Atoi().solution(str))
    str = "   +0 123"
    println(Atoi().solution(str))
    str = "9223372036854775808"
    println(Atoi().solution(str))
    str = "-"
    println(Atoi().solution(str))
}