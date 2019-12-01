import day1.DayOne

fun main() {
    val days: List<Day> = listOf<Day>(
        DayOne()
    )

    // run last day only.
    days.last().runDay()

    println("Do you want to run all days?[y/n]")
    val answer = readLine()?.split(" ")?.get(0)
    if (answer!! != "y") return
    for (day in days) {
        println("---- Day number ${day.dayNumber} ----")
        println(day.part1AsString())
        println(day.part2AsString())
        println("---------")

    }

}