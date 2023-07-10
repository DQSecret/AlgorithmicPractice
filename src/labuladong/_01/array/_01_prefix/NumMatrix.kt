package labuladong._01.array._01_prefix

/**
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 */
class NumMatrix(private val matrix: Array<IntArray>) {

    /**
     * 还是，先，凭直觉去写个大概
     */
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        var sum = 0
        for (i in row1..row2) {
            for (j in col1..col2) {
                sum += matrix[i][j]
            }
        }
        return sum
    }

    /**
     * 也是2个方向各增加一个位置，为了后续，不使用if分支
     */
    private val perSum: Array<IntArray> =
        Array(matrix.size + 1) { IntArray(matrix[0].size + 1) { 0 } }

    init {
        // 构造前缀和矩阵：有点类似于：(b-a)^2 = b2 - 2ab + a^2
        for (i in 1..perSum.lastIndex) {
            for (j in 1..perSum[0].lastIndex) {
                // 当前位置的值 = 本格子的值 + 列和 + 行和 - 左上角格子的值
                perSum[i][j] =
                    matrix[i - 1][j - 1] + perSum[i - 1][j] + perSum[i][j - 1] - perSum[i - 1][j - 1]
            }
        }
    }

    /**
     * 利用，前缀和的概念，去提前做好累加值的存储
     */
    fun sumRegion2(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return perSum[row2 + 1][col2 + 1] - perSum[row2 + 1][col1] - perSum[row1][col2 + 1] + perSum[row1][col1]
    }
}

fun main() {
//    NumMatrix(
//        arrayOf(
//            intArrayOf(1, 1, 1),
//            intArrayOf(1, 1, 1),
//            intArrayOf(1, 1, 1),
//        )
//    ).run {
//        println(sumRegion2(0, 0, 0, 0))
//        println(sumRegion2(0, 0, 1, 1))
//        println(sumRegion2(0, 0, 2, 2))
//        println(sumRegion2(1, 1, 2, 2))
//    }
    NumMatrix(
        arrayOf(
            intArrayOf(3, 0, 1, 4, 2),
            intArrayOf(5, 6, 3, 2, 1),
            intArrayOf(1, 2, 0, 1, 5),
            intArrayOf(4, 1, 0, 1, 7),
            intArrayOf(1, 0, 3, 0, 5),
        )
    ).run {
        println(sumRegion(2, 1, 4, 3))
        println(sumRegion(1, 1, 2, 2))
        println(sumRegion(1, 2, 2, 4))
        println(sumRegion2(2, 1, 4, 3))
        println(sumRegion2(1, 1, 2, 2))
        println(sumRegion2(1, 2, 2, 4))
    }
}