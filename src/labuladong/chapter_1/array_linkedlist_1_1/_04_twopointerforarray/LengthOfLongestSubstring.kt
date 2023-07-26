package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 无重复字符的最长子串 - [MinWindow]的同类型题目
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
class LengthOfLongestSubstring {

    /**
     * 所谓的不重复，不就是判断，windows中的值，是否为0么
     *
     * 这个题，更加简化了，少了很多判断点。
     */
    fun solution(s: String): Int {
        val windows = hashMapOf<Char, Int>()
        var left = 0
        var right = 0
        // 该案例中，这里的含义就是，重复的字符数量是多少个？
        var valid = 0
        // 这个用来判断，最长
        var start = 0
        var len = 0
        // 开始移动右边界
        while (right < s.length) {
            val c = s[right]
            right += 1
            windows[c] = windows.getOrDefault(c, 0) + 1
            if (windows[c]!! == 2) {
                valid += 1
            }
            // 开始移动左边界
            while (valid > 0) {
                val d = s[left]
                left += 1
                if (windows[d] == 2) {
                    valid -= 1
                }
                windows[d] = windows[d]!! - 1
            }
            // 记录
            if (right - left > len) {
                len = right - left
                start = left
            }
        }
        return len.also {
            println(s.substring(start, start + len))
        }
    }

    /**
     * 针对[solution]的优化简洁版
     */
    fun solution2(s: String): Int {
        val windows = hashMapOf<Char, Int>()
        var left = 0
        var right = 0
        var res = 0
        while (right < s.length) {
            val c = s[right]
            right += 1
            windows[c] = windows.getOrDefault(c, 0) + 1
            while (windows[c]!! > 1) {
                val d = s[left]
                left += 1
                windows[d] = windows[d]!! - 1
            }
            res = res.coerceAtLeast(right - left)
        }
        return res
    }
}

fun main() {
    LengthOfLongestSubstring().run {
        arrayOf("abcabcbb", "bbbbb", "pwwkew").onEach { str ->
            solution(str).also { println(it) }
            solution2(str).also { println(it) }
        }
    }
}