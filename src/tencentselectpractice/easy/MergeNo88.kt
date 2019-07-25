package tencentselectpractice.easy

import kotlin.system.measureTimeMillis

/**
 * 给定两个有序整数数组 nums1 和 nums2，
 * 将 nums2 合并到 nums1 中，
 * 使得 num1 成为一个有序数组。
 *
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
class Merge {

    /**
     * 原版思路
     */
    fun solution(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var end = m + n - 1 // 即将赋值的坐标
        var mt = m - 1 // num1 需要判断的下标
        var nt = n - 1 // num2 需要判断的下标

        // 只要 num2 还没遍历完, 就继续遍历
        while (nt >= 0 && mt >= 0) {
            // 取大值, 放到 nums1[end] 的位置
            if (nums1[mt] <= nums2[nt]) {
                nums1[end] = nums2[nt]
                nt -= 1 // nt 递减
            } else {
                nums1[end] = nums1[mt]
                mt -= 1 // mt 递减
            }
            // end 递减
            end -= 1
        }

        // 当 nt 还有数的时候, 需要把剩余的值, 赋值给前面
        while (nt >= 0) {
            nums1[end] = nums2[nt]
            nt -= 1
            end -= 1
        }
    }

    /**
     * 对上面的思路, 进行整理简化
     */
    fun solution2(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        // kotlin 参数不能修改
        var mt = m
        var nt = n
        // 尾指针
        var end = mt-- + nt-- - 1

        // 注意递减
        while (mt >= 0 && nt >= 0) {
            nums1[end--] = if (nums1[mt] > nums2[nt]) {
                nums1[mt--]
            } else {
                nums2[nt--]
            }
        }

        // 上面运行完, mt==-1 or nt==-1 ---> nt!=-1 则认为有遗漏的元素
        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, nt + 1)
    }
}

fun main() {
    val item = Merge()
//    val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
    val nums1 = intArrayOf(4, 5, 6, 0, 0, 0)
//    val nums2 = intArrayOf(4, 5, 6)
    val nums2 = intArrayOf(1, 2, 3)
//    val nums2 = intArrayOf(1, 1, 2)
//    val nums2 = intArrayOf(1, 1, 6)
//    val nums2 = intArrayOf(2, 3, 6)
//    val nums2 = intArrayOf(2, 5, 6)
//    val nums2 = intArrayOf(3, 5, 6)
    println("""
        nums1: ${nums1.contentToString()}
        nums2: ${nums2.contentToString()}
    """.trimIndent())

    val time = measureTimeMillis {
        item.solution2(nums1, nums1.size - nums2.size, nums2, nums2.size)
        println("""
            result: ${nums1.contentToString()}
        """.trimIndent())
    }

    println("solution in $time ms")
}