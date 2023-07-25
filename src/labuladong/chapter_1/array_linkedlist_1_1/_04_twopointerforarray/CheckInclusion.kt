package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 字符串的排列 - [MinWindow]的同类型题目
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 *
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
class CheckInclusion {

    /**
     * 借鉴[MinWindow]的思路，虽然题目是排列，看着好像无视顺序，但想一想，其实也没差，都是全覆盖元素
     *
     * 字串，就意味着连续？那就多加一个判断条件，windows.size==need.size
     */
    fun solution(s1: String, s2: String): Boolean {
        // 初始化2个map
        val need = hashMapOf<Char, Int>().apply {
            for (c in s1) this[c] = this.getOrDefault(c, 0) + 1
        }.also { println(it) }
        val windows = hashMapOf<Char, Int>()
        // 确定边界
        var left = 0
        var right = 0
        // 有效的元素个数
        var valid = 0
        // 从，扩大窗口开始
        while (right < s2.length) {
            // 扩大窗口
            val cr = s2[right]
            right += 1
            // 首先得在need中，其余都是无效项
            if (need.contains(cr)) {
                // 更新windows
                windows[cr] = windows.getOrDefault(cr, 0) + 1
                // 判断是不是满足了
                if (windows[cr] == need[cr]) {
                    valid += 1
                }
            }
            // 满足了，就开始缩小窗口
            while (valid == need.size) {
                // 因为要求是，子串，也就是说连续，即，不包含其他字符。
                // 那判断条件就简化成，全包含&&长度相等
                if ((right - left) == s1.length) return true.also {
                    s2.substring(left, right).also { println(it) }
                }
                // 缩小窗口
                val cl = s2[left]
                left += 1
                // 首先得在need中，其余都是无效项
                if (need.contains(cl)) {
                    if (windows[cl] == need[cl]) valid -= 1
                    windows[cl] = windows.getOrDefault(cl, 0) - 1
                }
            }
        }
        return false.also { println("") }
    }
}

fun main() {
    CheckInclusion().run {
        solution("ab", "eidbaooo").also { println(it) }
        solution("ab", "eidboaoo").also { println(it) }
        solution("hello", "ooolleoooleh").also { println(it) }
        solution("abcdxabcde", "abcdeabcdx").also { println(it) }
    }
}