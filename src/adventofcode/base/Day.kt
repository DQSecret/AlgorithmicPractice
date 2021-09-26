package adventofcode.base

import adventofcode.util.InputReader

abstract class Day(year: Int, day: Int) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(year, day) }
    protected val inputString: String by lazy { InputReader.getInputAsString(year, day) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}