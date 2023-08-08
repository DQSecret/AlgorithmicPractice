package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 数组 - 左右指针 - 两数之和II - 输入有序数组
 *
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，
 * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 *
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 */
class TwoSum {

    /**
     * 老规矩，先凭直觉。
     *
     * 1. 本题看起来，就是找2个元素，其之和刚好等于目标值
     * 2. 常量级的额外空间
     * 3. 思路，就是普通的双指针(快慢)，// 修正：发现快慢指针行不通，因为无法界定right走到什么时候，应该停止。
     * 3.1. 所以，改为左右指针，不断的缩小范围
     */
    fun solution(numbers: IntArray, target: Int): IntArray {
        // 左右指针
        var left = 0
        var right = numbers.lastIndex
        // 然后，开始缩小范围
        while (left < right) {
            val sum = numbers[left] + numbers[right]
            when {
                sum == target -> return intArrayOf(left + 1, right + 1)
                sum < target -> left += 1
                sum > target -> right -= 1
            }
        }
        // 返回结果
        return intArrayOf(-1, -1)
    }
}

fun main() {

    fun printResult(numbers: IntArray, target: Int, result: IntArray) {
        println("Index:${result.contentToString()}, 计算过程:${numbers[result[0] - 1]}+${numbers[result[1] - 1]}=$target")
    }

    TwoSum().run {
        (intArrayOf(2, 7, 11, 15) to 9).run {
            solution(first, second).also { printResult(first, second, it) }
        }
        (intArrayOf(2, 3, 4) to 6).run {
            solution(first, second).also { printResult(first, second, it) }
        }
        (intArrayOf(-1, 0) to -1).run {
            solution(first, second).also { printResult(first, second, it) }
        }
        (intArrayOf(5, 25, 75) to 100).run {
            solution(first, second).also { printResult(first, second, it) }
        }
        (intArrayOf(
            12,
            13,
            23,
            28,
            43,
            44,
            59,
            60,
            61,
            68,
            70,
            86,
            88,
            92,
            124,
            125,
            136,
            168,
            173,
            173,
            180,
            199,
            212,
            221,
            227,
            230,
            277,
            282,
            306,
            314,
            316,
            321,
            325,
            328,
            336,
            337,
            363,
            365,
            368,
            370,
            370,
            371,
            375,
            384,
            387,
            394,
            400,
            404,
            414,
            422,
            422,
            427,
            430,
            435,
            457,
            493,
            506,
            527,
            531,
            538,
            541,
            546,
            568,
            583,
            585,
            587,
            650,
            652,
            677,
            691,
            730,
            737,
            740,
            751,
            755,
            764,
            778,
            783,
            785,
            789,
            794,
            803,
            809,
            815,
            847,
            858,
            863,
            863,
            874,
            887,
            896,
            916,
            920,
            926,
            927,
            930,
            933,
            957,
            981,
            997
        ) to 542).run {
            solution(first, second).also { printResult(first, second, it) }
        }
        (intArrayOf(-10, -8, -2, 1, 2, 5, 6) to 0).run {
            solution(first, second).also { printResult(first, second, it) }
        }
    }
}