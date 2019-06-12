import kotlin.system.measureTimeMillis

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 */
class IsPalindrome {

    /**
     * 转化成 string 处理
     */
    fun solution(x: Int): Boolean {
        val aims = x.toString()
        var start = 0
        var end = aims.lastIndex
        while (end > start) {
            if (aims[start] != aims[end]) {
                return false
            }
            start += 1
            end -= 1
        }
        return true
    }

    /**
     * 取巧, 但是耗时反而长
     */
    fun solution2(x: Int): Boolean {
        return x.toString().reversed() == x.toString()
    }

    /**
     * 耗时最短
     */
    fun solution3(x: Int): Boolean {
        val string = x.toString()
        val length = string.length
        val array = string.toCharArray()
        for (i in 0 until length / 2) {
            array[i] = string[length - 1 - i]
            array[length - 1 - i] = string[i]
        }
        return String(array) == string
    }

    /**
     * 纯数字判断
     * tips: 后一半数字反转 == 前一半数字
     */
    fun solution4(x: Int): Boolean {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) return false

        var num = x
        var rev = 0
        while (num > rev) {
            rev = rev * 10 + num % 10
            num /= 10
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return num == rev || num == rev / 10 // 处理奇偶数
    }
}

fun main() {
    val time = measureTimeMillis { println(IsPalindrome().solution(121)) }
    println("solution in $time ms")
    val time2 = measureTimeMillis { println(IsPalindrome().solution2(1221)) }
    println("solution2 in $time2 ms")
    val time3 = measureTimeMillis { println(IsPalindrome().solution3(-121)) }
    println("solution3 in $time3 ms")
    val time4 = measureTimeMillis { println(IsPalindrome().solution4(10)) }
    println("solution4 in $time4 ms")
}