package day3

import Day
import java.io.File
import kotlin.math.abs

class DayThree : Day {
    override val dayNumber: Int
        get() = 3

    override fun runDay() {
        findShortestDist("R8,U5,L5,D3", "U7,R6,D4,L4")
        findShortestDist("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83")
        findShortestDist("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
        val input = File("src/day3/input.txt").readLines()
        println("The shortest distance is ${findShortestDist(input[0], input[1])}")
    }

    class Path(private val pathList: List<Instruction>) {

        fun getUsedCoordinates(x: Int, y: Int): MutableList<Pair<Int, Int>> {
            var originX = x
            var originY = y
            val usedCoordinates = mutableListOf<Pair<Int, Int>>()
            for (instruction in pathList) {
                when (instruction.direction) {
                    'R' -> {
                        val oldOrigin = originX
                        originX += instruction.amount
                        for (i in oldOrigin..originX) {
                            usedCoordinates.add(Pair(i, originY))
                        }
                    }
                    'U' -> {
                        val oldOrigin = originY
                        originY += instruction.amount
                        for (i in oldOrigin..originY) {
                            usedCoordinates.add(Pair(originX, i))
                        }
                    }
                    'L' -> {
                        val oldOrigin = originX
                        originX -= instruction.amount
                        for (i in originX..oldOrigin) {
                            usedCoordinates.add(Pair(i, originY))
                        }
                    }
                    'D' -> {
                        val oldOrigin = originY
                        originY -= instruction.amount
                        for (i in originY..oldOrigin) {
                            usedCoordinates.add(Pair(originX, i))
                        }
                    }
                }
            }
            return usedCoordinates
        }

        override fun toString(): String {
            return pathList.map { it.toString() }.toString()
        }

        companion object {
            fun fromString(string: String): Path {
                return Path(string.split(",").map { Instruction.fromString(it) })
            }
        }
    }

    class Instruction(val direction: Char, val amount: Int) {

        companion object {
            fun fromString(string: String): Instruction {
                val dir = string[0]
                val slice = string.slice(1..string.lastIndex)
                val amt = slice.toInt()
                return Instruction(dir, amt)
            }
        }

        override fun toString(): String {
            return direction.toString() + amount.toString()
        }
    }

    fun calcDistanceTo(x: Int, y: Int, coordinates: Set<Pair<Int, Int>>): Int {
        val mutableSet = coordinates.toMutableSet()
        mutableSet.remove(Pair(x, y))
        val shortest = mutableSet.map { Pair(abs(it.first), abs(it.second)) }.map { it.first + it.second }.min()
        return shortest!!
    }

    fun findShortestDist(path1: String, path2: String): Int {
        val path1p = Path.fromString(path1)
        val path2p = Path.fromString(path2)
//        println(path1p)
//        println(path2p)
        val x = 0
        val y = 0
        val coordinates1 = path1p.getUsedCoordinates(x, y)
        val coordinates2 = path2p.getUsedCoordinates(x, y)
        val coordinatesIntersection = coordinates1.intersect(coordinates2)
        return calcDistanceTo(x, y, coordinatesIntersection)
    }

    override fun part1AsString(): String {
        val input = File("src/day3/input.txt").readLines()
        return "The answer to part 1 is ${findShortestDist(input[0], input[1])}"
    }

    override fun part2AsString(): String {
        return "p2"
    }
}