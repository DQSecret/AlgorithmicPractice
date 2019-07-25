/**
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 * 桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
 * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
 *
 * 示例:
 * 输入: 4
 * 输出: false
 * 解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
 *       因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 */
class CanWinNim {

    /**
     * 感觉是 贪心 or 动态规划
     * 只要保证最后一个是我拿的就算赢, 那就往回倒推
     * 好像不是
     * 条件,
     *      1. 我是先手
     *      2. 都是聪明人，每一步都是最优解
     * 有点类似, 小时候玩的, 谁先数到 20 谁赢的游戏
     * 从后往前推导
     *
     * 虽然得出最有结论, 为了练习, dp, 尝试写一下
     */
    fun solution(n: Int): Boolean {
        var result = false
        when (n) {
            1, 2, 3 -> {
                result = true
            }
            4 -> {
                result = false
            }
            5, 6, 7 -> {
                // 先拿 1,2,3 个, 剩余 4 个, 回到4问题, 必赢
                result = true
            }
            8 -> {
                result = false
            }
            9, 10, 11 -> {
                // 先拿 1,2,3 个, 剩余 8 个, 回到4问题, 必赢
                result = true
            }
            12 -> {
                result = false
            }
        }
        return result
    }

    /**
     * 根据以上结论, 只要是 4 的倍数 必输
     */
    fun solution1(n: Int): Boolean {
        return n % 4 != 0
    }

    /**
     * dp: 效率不高,但是还是打算了;练习一下
     */
    fun solution2(n: Int): Boolean {
        if (n <= 3) return true
        return !solution2(n - 1) || !solution2(n - 2) || !solution2(n - 3)
    }
}

fun main() {
    testCanWinNim()
}

fun testCanWinNim() {
    println(CanWinNim().solution1(4))
    println(CanWinNim().solution2(20))
}
