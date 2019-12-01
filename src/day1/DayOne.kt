package day1

import Day
import java.io.File
import kotlin.math.floor

//Santa has become stranded at the edge of the Solar System while delivering presents to other planets! To accurately calculate his position in space, safely align his warp drive, and return to Earth in time to save Christmas, he needs you to bring him measurements from fifty stars.
//
//Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
//
//The Elves quickly load you into a spacecraft and prepare to launch.
//
//At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper. They haven't determined the amount of fuel required yet.
//
//Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
//
//For example:
//
//For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
//For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
//For a mass of 1969, the fuel required is 654.
//For a mass of 100756, the fuel required is 33583.
//The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.
//
//What is the sum of the fuel requirements for all of the modules on your spacecraft?

class DayOne : Day {
    override val dayNumber: Int
        get() = 1

    override fun runDay() {
        println("Running day 1")
        val testMasses: List<Int> = listOf(12, 14, 1969, 100756)
        for (mass in testMasses) {
            println("Fuel needed with mass ${mass}: ${calcFuel(mass)}")
        }

        for (mass in testMasses) {
            println("Fuel needed when accounting for fuel mass is ${calcFuelIncludingFuel(mass)}")
        }

        println(part1AsString())
        println(part2AsString())
    }

    override fun part1AsString(): String {
        val inputAsMass = File("src/day1/input.txt").readLines().map { it.toInt() }
        return "The total amount of fuel is ${calcTotalAmountOfFuel(inputAsMass)}"
    }

    override fun part2AsString(): String {
        val inputAsMass = File("src/day1/input.txt").readLines().map { it.toInt() }
        return "The total amount of fuel accounted for fuel mass is ${calcTotalAmountOfFuelIncludingFuelMass(inputAsMass)}"
    }

    private fun calcTotalAmountOfFuel(totalMass: List<Int>): Long {
        var sum: Long = 0;
        for (mass in totalMass) sum += calcFuel(mass)
        return sum
    }

    private fun calcTotalAmountOfFuelIncludingFuelMass(totalMass: List<Int>): Long {
        var sum: Long = 0;
        for (mass in totalMass) sum += calcFuelIncludingFuel(mass)
        return sum
    }

    private fun calcFuelIncludingFuel(mass: Int): Int {
        val fuelForMass = calcFuel(mass)
        if (fuelForMass < 0) {
            return 0
        }
        return fuelForMass + calcFuelIncludingFuel(fuelForMass)
    }

    private fun calcFuel(mass: Int): Int {
        //take its mass, divide by three, round down, and subtract 2.
        //For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
        //For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
        //For a mass of 1969, the fuel required is 654.
        //For a mass of 100756, the fuel required is 33583.
        val fuel = floor(mass / 3.0) - 2.0;
        return fuel.toInt()
    }
}