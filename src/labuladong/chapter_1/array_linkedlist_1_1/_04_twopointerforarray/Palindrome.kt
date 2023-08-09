package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 回文串的相关例题 - 左右指针(从中间向2端扩散)
 */
class Palindrome {

    /**
     * 先来个简单的，判断回文串
     */
    fun isPalindrome(x: Int): Boolean {
        val str = x.toString()
        var left = 0
        var right = str.lastIndex
        while (left < right) {
            if (str[left] != str[right]) return false
            left += 1
            right -= 1
        }
        return true
    }

    /**
     * 最长回文子串
     *
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 直觉的思路如下：
     * 1. 从外往里，缩减范围，会导致，不清楚该缩左边，还是右边
     * 2. 从里往外？这个“里”从哪儿开始呢？
     * 3. 最后决定，先遍历，用遍历到的元素，作为中心，向外扩展，判断是不是回文。先试试看呗。
     */
    fun longestPalindrome(s: String): String {
        // 特殊情况
        if (s.length == 1) return s
        // 准备记录值
        var subString = s.first().toString()
        // 外层遍历
        for (i in 0..s.lastIndex - 1) {
            // ① mid 即是中间元素；
            if (i >= 1) {
                var gap = 1
                while ((i - gap) >= 0 && (i + gap) <= s.lastIndex && s[i - gap] == s[i + gap]) {
                    if (subString.length < gap * 2 + 1) {
                        subString = s.substring(i - gap, i + gap + 1)
                    }
                    gap += 1
                }
            }
            // ② mid 和下一个元素相同，均为中间元素
            var gap = 0
            while ((i - gap) >= 0 && (i + 1 + gap) <= s.lastIndex && s[i - gap] == s[i + 1 + gap]) {
                if (subString.length < gap * 2 + 2) {
                    subString = s.substring(i - gap, i + gap + 2)
                }
                gap += 1
            }
        }
        // 返回结果
        return subString
    }

    /**
     * 优化版
     */
    fun longestPalindrome2(s: String): String {

        /**
         * 这个函数是核心，
         * 1. 当，l!=r，查找的是，偶数回文，即中间的2个元素，相同
         * 1. 当，l==r，查找的是，奇数回文，即中间的1个元素，相同
         */
        fun inline(s: String, l: Int, r: Int): String {
            var left = l
            var right = r
            // 注意越界问题
            while (left >= 0 && right <= s.lastIndex && s[left] == s[right]) {
                left--
                right++
            }
            // 这里为什么 left+1 但 right 不加 1？
            // 1. substring 是一个左闭右开的区间，
            // 3. +1，是因为，在上面的 while 中判断相等后，left--，当下一轮不符合了，left 已经被左移了 1 个下标
            return s.substring(left + 1, right)
        }

        // 至此，核心逻辑如上，则，使用流程如下：
        var result = ""
        for (i in 0..s.lastIndex) {
            val result1 = inline(s, i, i)
            val result2 = inline(s, i, i + 1)
//            val temp = if (result1.length > result2.length) result1 else result2
//            if (result.length < temp.length) result = temp
            // 简化一下上面的比较，会减少额外的temp的空间
            if (result.length < result1.length) result = result1
            if (result.length < result2.length) result = result2
        }
        return result
    }
}

fun main() {
    Palindrome().run {
        listOf(121, -121, 10).forEach { item ->
            isPalindrome(item).also { println("$item -> $it") }
        }
        listOf("babad", "cbbd", "a", "ac").forEach { item ->
            longestPalindrome(item).also { println("$item -> $it") }
            longestPalindrome2(item).also { println("$item -> $it") }
        }
    }
}