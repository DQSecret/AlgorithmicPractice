package forcesearch

/**
 * DFS 算法练习题
 *
 * 解数独
 *
 * @author DQ For Olivia
 * @since 2022/1/17 5:50 下午
 * @see <a href="https://leetcode-cn.com/problems/sudoku-solver/">力扣题目</a>
 * @see <a href="https://labuladong.gitee.io/algo/4/29/109/">讲解</a>
 */
class SolveSudoku {

    /**
     * 数独规则大家都懂,解法的话,其实还是回溯算法,老套路走起~
     * 输入: board = [
     *          ["5","3",".",".","7",".",".",".","."],
     *          ["6",".",".","1","9","5",".",".","."],
     *          [".","9","8",".",".",".",".","6","."],
     *          ["8",".",".",".","6",".",".",".","3"],
     *          ["4",".",".","8",".","3",".",".","1"],
     *          ["7",".",".",".","2",".",".",".","6"],
     *          [".","6",".",".",".",".","2","8","."],
     *          [".",".",".","4","1","9",".",".","5"],
     *          [".",".",".",".","8",".",".","7","9"]
     *      ]
     *
     * 输出: board = [
     *          ["5","3","4","6","7","8","9","1","2"],
     *          ["6","7","2","1","9","5","3","4","8"],
     *          ["1","9","8","3","4","2","5","6","7"],
     *          ["8","5","9","7","6","1","4","2","3"],
     *          ["4","2","6","8","5","3","7","9","1"],
     *          ["7","1","3","9","2","4","8","5","6"],
     *          ["9","6","1","5","3","7","2","8","4"],
     *          ["2","8","7","4","1","9","6","3","5"],
     *          ["3","4","5","2","8","6","1","7","9"]
     *      ]
     *
     */
    fun solveSudoku(board: Array<CharArray>) {
        val result = backtrack(board, 0, 0)
        if (result) {
            val msg = """已计算出结果:
                |${board.joinToString("\n") { it.joinToString(prefix = "[", postfix = "]") }}"""
                .trimMargin()
            println(msg)
        } else {
            println("题目设计错误,无解...")
        }
    }

    /**
     * 解题套路
     */
    private fun backtrack(board: Array<CharArray>, x: Int, y: Int): Boolean {
        // 换行
        if (y == 9) {
            return backtrack(board, x + 1, 0)
        }
        // 全都找到了
        if (x == 9) {
            return true
        }
        // 有值了,下一个
        if (board[x][y] != '.') {
            return backtrack(board, x, y + 1)
        }
        // 无值的话,则需要,从1..9挨个试
        for (i in '1'..'9') {
            // 如果重复,则下一个
            if (!isValid(board, x, y, i)) {
                continue
            }
            // 没重复,则做出选择
            board[x][y] = i
            // 回溯
            if (backtrack(board, x, y + 1)) {
                return true
            }
            board[x][y] = '.'
        }
        return false
    }

    /**
     * 获取下一个坐标
     */
    private fun getNextXY(x: Int, y: Int): Pair<Int, Int> {
        val nextX: Int
        val nextY: Int
        if (y == 8) {
            nextX = x + 1
            nextY = 0
        } else {
            nextX = x
            nextY = y + 1
        }
        return nextX to nextY
    }

    /**
     * 判断 board[x][y] 是否可以填入target
     * 比下面的三个判断,更高效
     */
    private fun isValid(board: Array<CharArray>, x: Int, y: Int, target: Char): Boolean {
        for (i in 0..8) {
            // 判断行是否存在重复
            if (board[x][i] == target) return false
            // 判断列是否存在重复
            if (board[i][y] == target) return false
            // 判断 3 x 3 方框是否存在重复
            if (board[x / 3 * 3 + i / 3][y / 3 * 3 + i % 3] == target) return false
        }
        return true
    }

    /**
     * 是否重复
     */
    private fun isRepeated(board: Array<CharArray>, x: Int, y: Int, target: Char): Boolean {
        return isContainedInRow(board, x, target)
                || isContainedInColumn(board, y, target)
                || isContainedInScope(board, x, y, target)
    }

    /**
     * 判断一行中,是否含有目标值
     */
    private fun isContainedInRow(board: Array<CharArray>, x: Int, target: Char): Boolean {
        for (col in 0..8) {
            if (board[x][col] == target) {
                return true
            }
        }
        return false
    }

    /**
     * 判断一列中,是否含有目标值
     */
    private fun isContainedInColumn(board: Array<CharArray>, y: Int, target: Char): Boolean {
        for (row in 0..8) {
            if (board[row][y] == target) {
                return true
            }
        }
        return false
    }

    /**
     * 判断九宫格中,是否含有目标值
     */
    private fun isContainedInScope(board: Array<CharArray>, x: Int, y: Int, target: Char): Boolean {
        for (row in 0..2) {
            for (col in 0..2) {
                if (board[x / 3 * 3 + row][y / 3 * 3 + col] == target) {
                    return true
                }
            }
        }
        return false
    }
}

fun main() {
//    val board: Array<CharArray> = arrayOf(
//        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
//        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
//        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
//        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
//        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
//        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
//        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
//        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
//        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9'),
//    )
    val board: Array<CharArray> = arrayOf(
        charArrayOf('.', '.', '9', '7', '4', '8', '.', '.', '.'),
        charArrayOf('7', '.', '.', '.', '.', '.', '.', '.', '.'),
        charArrayOf('.', '2', '.', '1', '.', '9', '.', '.', '.'),
        charArrayOf('.', '.', '7', '.', '.', '.', '2', '4', '.'),
        charArrayOf('.', '6', '4', '.', '1', '.', '5', '9', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '3', '.', '.'),
        charArrayOf('.', '.', '.', '8', '.', '3', '.', '2', '.'),
        charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '6'),
        charArrayOf('.', '.', '.', '2', '7', '5', '9', '.', '.')
    )
    with(SolveSudoku()) {
        solveSudoku(board)
    }
}
