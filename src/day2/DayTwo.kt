package day2

import Day
import java.io.File

class DayTwo : Day {
    override val dayNumber: Int
        get() = 2

    class Opcodes {
        private val addCode: Int = 1;
        private val multiplyCode: Int = 2;
        private val haltCode: Int = 99;

        fun handle(data: MutableList<Int>): MutableList<Int> {
            var pos = 0;

            processLoop@ while (pos != data.lastIndex) {
                val currentOpcode = data[pos]
                if (currentOpcode == haltCode) {
                    break@processLoop
                }
                val first = data[pos + 1]
                val second = data[pos + 2]
                val location = data[pos + 3]
                val firstData = data[first]
                val secondData = data[second]

                when (currentOpcode) {
                    addCode -> {
                        val sum = firstData + secondData
                        data[location] = sum
                        pos += 4
                    }
                    multiplyCode -> {
                        val multiplication = firstData * secondData
                        data[location] = multiplication
                        pos += 4
                    }
                    else -> {
                        throw Exception("Oh no")
                    }
                }
            }
            return data
        }
    }

    override fun runDay() {
        println(part1())
        println(part2())
    }

    private fun part1(): String {
        val codes = Opcodes()
        val dataInput =
            File("src/day2/input.txt").readLines().map { it.split(",") }.first().map { it.toInt() }.toMutableList()
        dataInput[1] = 12 // noun
        dataInput[2] = 2 // verb
        val ret = codes.handle(dataInput)
        return "The answer to part one is ${ret[0]}"
    }

    private fun part2(): String {
        val codes = Opcodes()
        val dataInput =
            File("src/day2/input.txt").readLines().map { it.split(",") }.first().map { it.toInt() }
        val wantedOutput = 19690720
        var solution = -1
        for (i in 0..99) {
            for (j in 0..99) {
                val currentInput = dataInput.toMutableList()
                currentInput[1] = i // noun
                currentInput[2] = j // verb
                val ret = codes.handle(currentInput)
                val output = ret[0]
                if (output == wantedOutput) {
                    solution = (100 * i) + j
                }
            }
        }
        return "The answer to part two is $solution"
    }

    override fun part1AsString(): String = part1()

    override fun part2AsString(): String = part2()

    fun test(codes: Opcodes) {
        var testExample = listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50).toMutableList()
        var ret = codes.handle(testExample)
        var expected = listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)
        println(ret == expected)

        testExample = listOf(1, 0, 0, 0, 99).toMutableList()
        ret = codes.handle(testExample)
        expected = listOf(2, 0, 0, 0, 99)
        println(ret == expected)

        testExample = listOf(1, 1, 1, 4, 99, 5, 6, 0, 99).toMutableList()
        ret = codes.handle(testExample)
        expected = listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
        println(ret == expected)

        testExample = listOf(2, 4, 4, 5, 99, 0).toMutableList()
        ret = codes.handle(testExample)
        expected = listOf(2, 4, 4, 5, 99, 9801)
        println(ret == expected)
    }
}