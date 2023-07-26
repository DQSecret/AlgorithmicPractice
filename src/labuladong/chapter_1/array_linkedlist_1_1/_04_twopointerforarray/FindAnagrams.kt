package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 找到字符串中所有字母异位词 - [MinWindow]的同类型题目
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
class FindAnagrams {

    /**
     * 还是根据滑动窗口的思路，去解题...
     *
     * 当连续做了几个，滑动窗口的题目时，已经越来越熟练了。╮(╯▽╰)╭
     */
    fun solution(s: String, p: String): List<Int> {
        // 前置
        val need = hashMapOf<Char, Int>().apply {
            for (c in p) this[c] = this.getOrDefault(c, 0) + 1
        }
        val windows = hashMapOf<Char, Int>()
        // 边界、满足元素的个数
        var left = 0
        var right = 0
        var valid = 0
        // 记录结果
        val results = mutableListOf<Int>()
        // 开始移动窗口，先移动右边界
        while (right < s.length) {
            // 扩大
            val c = s[right]
            if (p.contains(c)) {
                windows[c] = windows.getOrDefault(c, 0) + 1
                if (windows[c] == need[c]) {
                    valid += 1
                }
            }
            right += 1
            // 开始移动左边界
            while (valid == need.size) {
                // 满足了，就记录下来
                if (right - left == p.length) results.add(left)
                // 缩减
                val d = s[left]
                if (p.contains(d)) {
                    if (windows[d] == need[d]) {
                        valid -= 1
                    }
                    windows[d] = windows.getOrDefault(d, 0) - 1
                }
                left += 1
            }
        }
        // 返回结果
        return results.also { list ->
            list.map { s.substring(it, it + p.length) }.also { println(it) }
        }
    }
}

fun main() {
    FindAnagrams().run {
        solution("cbaebabacd", "abc").also { println(it) }
        solution("abab", "ab").also { println(it) }
    }
}