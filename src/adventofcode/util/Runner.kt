package adventofcode.util

import adventofcode.base.Day
import kotlin.math.max
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

object Runner {

    @ExperimentalTime
    fun printDay(day: Day) = with(day) {
        val partOne = measureTimedValue { day.partOne() }
        val partTwo = measureTimedValue { day.partTwo() }
        printParts(partOne, partTwo)
    }

    @ExperimentalTime
    fun printParts(partOne: TimedValue<Any>, partTwo: TimedValue<Any>) {
        // 14 is 8 (length of 'Part 1: ') + 6 more
        val padding = max(
            partOne.value.toString().length,
            partTwo.value.toString().length
        ) + 14
        println("Part 1: ${partOne.value}".padEnd(padding, ' ') + "(${partOne.duration})")
        println("Part 2: ${partTwo.value}".padEnd(padding, ' ') + "(${partTwo.duration})")
    }
}