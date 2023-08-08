package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 反转字符串 - 左右指针
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 示例 1：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 示例 2：
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
class ReverseString {

    /**
     * 老规矩，先凭直觉
     *
     * 1. 题目要求：原地
     * 2. 空间复杂度：O(1)
     */
    fun solution(s: CharArray): Unit {
        var left = 0
        var right = s.lastIndex
        while (left < right) {
            if (s[left] != s[right]) {
                val temp = s[left]
                s[left] = s[right]
                s[right] = temp
            }
            left += 1
            right -= 1
        }
    }
}

fun main() {
    ReverseString().run {
        val s1 = "hello".toCharArray()
        solution(s1)
        println(s1)
        val s2 = "Hannah".toCharArray()
        solution(s2)
        println(s2)
    }
}