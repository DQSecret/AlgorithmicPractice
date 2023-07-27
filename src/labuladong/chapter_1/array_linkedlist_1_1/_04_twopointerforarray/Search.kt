package labuladong.chapter_1.array_linkedlist_1_1._04_twopointerforarray

/**
 * 用标准的【二分查找】来进入，左右指针的世界
 *
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 */
class Search {

    /**
     * 老规矩，先凭直觉
     *
     * 1. 那，默认已经是升序的了，凭直接就是for循环，就完了呗
     * 2. 然后，进阶以下，就是2分么，要是用递归？
     *
     * 唉，补充了好几个细节点，才通过...
     */
    fun solution(nums: IntArray, target: Int): Int {
        // 最开始的疑问：
        // 首先确认中点，这就需要考虑，奇偶的问题了，
        // 如果直接除2，比如，4个元素/2=2，那下标为2，就得是，第三个元素，此时，左区间(0 until 2)；右(2 until size)
        // 如果是5个元素，5/2=2；下标为2，左区间(0 until 2)，右区间(2 until size)，这样就少判断一个中间元素
        // 如果是：[0..mid] 和 [mid..lastIndex] 都是闭区间呢？
        // 所以，如何用统一的规则，去建立2个区间呢？
        // 0123 // (4)/2=2 -- 2 左 01 右 3
        // 01234 // (5)/2=2 -- 2 左 01 右 34
        // 1.特殊判断 // 这里的逻辑，是，编译后补充的
        if (nums.size == 1) {
            return if (nums[0] == target) 0 else -1
        }
        // 2.确立左右边界
        var left = 0
        var right = nums.lastIndex
        // 3.开始循环
        while (left != right) {
            // 找到了
            val mid = (left + right) / 2
            if (nums[mid] == target) return mid
            // 去左边界
            if (target < nums[mid]) {
                right = if (left + 1 == right) left // 这里的逻辑，是，编译后补充的
                else mid
            }
            // 去右边界
            if (target > nums[mid]) {
                left = if (left + 1 == right) right // 这里的逻辑，是，编译后补充的
                else mid
            }
        }
        // 4.最中间的那个值，是否刚好等于 // 这里的逻辑，是，编译后补充的
        if (nums[left] == target) return left
        // 返回结果
        return -1
    }

    /**
     * 对[solution]的精简优化
     */
    fun solution2(nums: IntArray, target: Int): Int {
        // 1. 区间的左右边界要确定好，这都是容易出错的地方
        var left = 0
        var right = nums.lastIndex // [0,nums.lastIndex] 闭区间
        // var right = nums.size // [0,nums.size) 这就是左闭右开区间
        // 2. 确定好循环条件(和，终止条件，意义刚好相对)， 即，终止条件是：left > right
        while (left <= right) {
            // 3. 取中点
            val mid = left + (right - left) / 2
            // var mid = (left + right) / 2 // 和上一行效果一致，但上一行可以防止 left+right > Int.MAX_VALUE
            // 4. 核心：如果收紧区间
            if (nums[mid] == target) {
                return mid // 找到结果了
            } else if (nums[mid] < target) {
                // 中值，小于目标值，即，min[mid] < target < min[right]
                // 为什么要+1呢？就是因为，mid已经被判断过了，所以收紧区间，就可以排除mid了
                left = mid + 1
            } else if (nums[mid] > target) {
                // 同理，改变右边界
                right = mid - 1
            }
        }
        // 5. 如果没找到
        return -1
    }
}

fun main() {
    Search().run {
        (intArrayOf(-1, 0, 3, 5, 9, 12) to 9).run {
            solution(first, second).also { println(it) }
            solution2(first, second).also { println(it) }
        }
        (intArrayOf(-1, 0, 3, 5, 9, 12) to 2).run {
            solution(first, second).also { println(it) }
            solution2(first, second).also { println(it) }
        }
        (intArrayOf(5) to 5).run {
            solution(first, second).also { println(it) }
            solution2(first, second).also { println(it) }
        }
        (intArrayOf(2, 5) to 5).run {
            solution(first, second).also { println(it) }
            solution2(first, second).also { println(it) }
        }
    }
}