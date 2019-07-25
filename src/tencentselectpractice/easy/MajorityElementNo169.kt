package tencentselectpractice.easy

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。

 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3

 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
class MajorityElement {

    /**
     * 两层 for 循环就不说了, 太暴力了
     * 方法 1: 利用 hash 保存元素出现次数
     * 时间O(n) - 空间O(n)
     */
    fun solution1(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        val maxCount = nums.size / 2
        for (num in nums) {
            val count = map.getOrPut(num, { 0 }) + 1
            if (count > maxCount) {
                return num
            } else {
                map[num] = count
            }
        }
        return -1
    }

    /**
     * 方法 2: 先排序, 然后取中间值 [n/2]
     * 极端判断:
     *                        ________众数为max的覆盖情况
     *                 [0,1,2,3,4,5,6]
     * 众数为min的覆盖情况ˉˉˉˉˉˉˉˉ
     *                      ________众数为max的覆盖情况
     *                 [0,1,2,3,4,5]
     * 众数为min的覆盖情况ˉˉˉˉˉˉˉˉ
     *
     * 结论: 无论 size 为奇or偶, [n/2] 位置均为众数
     */
    fun solution2(nums: IntArray): Int {
        nums.sort()
        return nums[nums.size / 2]
    }

    /**
     * 方法 3: 分治 - 分治算法递归求解，直到所有的子问题都是长度为 1 的数组
     */
    fun solution3(nums: IntArray): Int {
        return majority(nums, 0, nums.lastIndex)
    }

    private fun majority(nums: IntArray, start: Int, end: Int): Int {
        // 个数为 1
        if (start == end) return nums[start]
        // 分治
        val mid = (end - start) / 2 + start
        val leftMaj = majority(nums, start, mid)
        val rightMaj = majority(nums, mid + 1, end)
        // 左右众数判断, 相等 - 直接返回,
        if (leftMaj == rightMaj) return leftMaj
        // 不等 - 统计出现次数, 取多的
        val leftCount = count(leftMaj, nums, start, mid)
        val rightCount = count(rightMaj, nums, mid + 1, end)
        return if (leftCount > rightCount) leftMaj else rightMaj
    }

    private fun count(aim: Int, nums: IntArray, start: Int, end: Int): Int {
        var count = 0
        for (i in start..end) {
            if (nums[i] == aim) count++
        }
        return count
    }

    /**
     * 方法 4: Boyer-Moore 投票算法
     * 解释:
     *      众数出现的次数 比 其余所有元素出现的次数总和还多,
     *      利用计数器, 众数+1, 其余-1, 结果 >0
     *      基于以上理论, 假设首元素为众数, 向后遍历, 相等+1, 不等-1
     *      当计数器为 0 时, (抵消,无论目前为止的元素中,是不是存在真正的众数,都当做牺牲品)
     *      取下一个值为新的假设众数, 继续遍历
     * 举例:
     *      竖线用来划分每次计数器归零的情况
     *      [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
     */
    fun solution4(nums: IntArray): Int {
        var majority = 0
        var count = 0
        nums.forEach {
            if (count == 0) majority = it
            if (majority == it) count++ else count--
        }
        return majority
    }

    /**
     * 对 方法 4 的代码优化: 减少判断
     */
    fun solution5(nums: IntArray): Int {
        var majority = 0
        var count = 0
        for (element in nums) { // 减少一个闭包
            when { // 减少了一些, count == 0 重置的时机
                majority == element -> count++
                count == 0 -> majority = element
                else -> count--
            }
        }
        return majority
    }
}

fun main() {
    testMajorityElement()
}

fun testMajorityElement() {
    val arrA = intArrayOf(3, 2, 3)
    val arrB = intArrayOf(2, 2, 1, 1, 1, 2, 2)
    val arrC = intArrayOf(1, 5, 2, 5, 3, 5, 4, 5, 5)
    val arrD = intArrayOf(7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7)
    MajorityElement().run {
        println(solution1(arrA))
        println(solution2(arrB))
        println(solution3(arrC))
        println(solution4(arrD))
    }
}