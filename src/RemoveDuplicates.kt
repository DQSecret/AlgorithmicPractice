import java.util.*
import kotlin.system.measureTimeMillis

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * ps: 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
class RemoveDuplicates {

    /**
     * 自己想的
     */
    fun solution(nums: IntArray): Int {
        if (nums.size < 2) return nums.size
        var head = 1
        var aim = nums[0]
        for (value in nums) {
            if (aim != value) {
                nums[head] = value
                aim = value
                head++
            }
        }
        return head
    }

    /**
     * 官方的,
     * 少一个变量, 少一次循环, 但是会多次取值
     */
    fun solution2(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var i = 0
        for (j in 1 until nums.size) {
            if (nums[j] != nums[i]) {
                i++
                nums[i] = nums[j]
            }
        }
        return i + 1
    }
}

fun main() {
    val time = measureTimeMillis {
        val nums = intArrayOf(0, 1, 1, 2, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 8, 9, 10)
        val size = RemoveDuplicates().solution(nums)
        println("不重复的元素有 $size 个, 其结果为: ${Arrays.toString(nums)}")
    }
    println("solution in $time ms")
}