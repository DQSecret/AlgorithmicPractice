/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1

 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16

 * 示例 3:
 * 输入: 218
 * 输出: false
 */
class IsPowerOfTwo {

    /**
     * 方法 1: 转为float, 连续/2, == 0 即符合条件
     */
    fun solution1(n: Int): Boolean {
        var nf = n.toFloat()
        while (nf >= 1) {
            if (nf == 1f) return true
            nf /= 2
        }
        return false
    }

    /**
     * 方法 2: 转成二进制 计算 1 出现的次数
     * 1  -----     1
     * 2  -----    10
     * 4  -----   100
     * 8  -----  1000
     * 16 ----- 10000
     * 2 的幂次方 二进制只有一个 1
     */
    fun solution2(n: Int): Boolean {
        if (n < 1) return false
        return n.toString(2).count { it == '1' } == 1
        // println((-16).toString(2)) : -10000
        // 还有一个替代方案 n & (n-1) = 0
        // 8=1000 8-1=7=0111
        // 1000 & 0111 = 0000 = 0
    }

    /**
     * 方法 3: 在方法 2 的基础上, 替换
     */
    fun solution3(n: Int): Boolean {
        return n >= 1 && Integer.bitCount(n) == 1
        // Integer.bitCount(n) jdk 内置 统计二进制 1 的个数
    }
}

fun main() {
    //testIsPowerOfTwo()
    println((-16).toString(2))
}

fun testIsPowerOfTwo() {
    println(IsPowerOfTwo().solution3(1))
    println(IsPowerOfTwo().solution3(16))
    println(IsPowerOfTwo().solution3(-16))
    println(IsPowerOfTwo().solution3(218))
}
