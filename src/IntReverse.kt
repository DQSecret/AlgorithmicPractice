/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * tips：
 * f1 - 旧值连续/10，新值连续*10，利用%10取最后一位
 * f2 - 取巧，利用 string 的 reverse() 计算，有点汗颜 = =！
 * f3 - 不使用 toLong 处理边界问题，利用数学计算来判断溢出
 *
 * ps：边界判断
 *      |-- Int.MAX_VALUE(2147483647) - (10)位数 - 最后一位(7)
 *      |-- Int.MIN_VALUE(-2147483648) - (10)位数 - 最后一位(8)
 */
class IntReverse {
    fun solution(x: Int): Int {
        var num = x // kotlin 不允许更改参数
        var rev = 0 // 反转后的值
        while (num != 0) {
            val pop = num % 10
            num /= 10
            if (rev > Int.MAX_VALUE / 10 || (rev == Int.MAX_VALUE / 10 && pop > 7)) return 0
            if (rev < Int.MIN_VALUE / 10 || (rev == Int.MIN_VALUE / 10 && pop < 8)) return 0
            rev = rev * 10 + pop
        }
        return rev
    }
}

fun main() {
    println(IntReverse().solution(123))
    println(IntReverse().solution(-123))
    println(IntReverse().solution(120))
    println(IntReverse().solution(-2147483648))
}