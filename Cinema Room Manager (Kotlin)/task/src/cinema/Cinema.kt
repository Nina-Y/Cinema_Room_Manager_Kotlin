package cinema
const val PRICE_FRONT = 10
const val PRICE_BACK = 8
var countSold = 0
var currentIncome = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    var cinema = MutableList(rows) { MutableList(seats) { 'S' } }
    val totalSeats = rows * seats
    
    while (true) {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        val option = readln().toInt()
        when (option) {
            1 -> printCinema(cinema, seats)
            2 -> buyTicket(cinema, rows, seats, totalSeats)
            3 -> showStatistics(countSold, currentIncome, rows, seats, totalSeats)
            0 -> break
        }
    }  
}

fun showStatistics(countSold: Int, currentIncome: Int, rows: Int, seats: Int, totalSeats: Int) {
    println("Number of purchased tickets: $countSold")
    var percentage = (countSold.toDouble() * 100 / totalSeats)
    val formatPercentage = "%.2f".format(percentage)
    println("Percentage: $formatPercentage%")   
    println("Current income: $$currentIncome")
    var totalIncome = totalSeats * PRICE_FRONT
    if (totalSeats > 60) {
        totalIncome = rows / 2 * seats * PRICE_FRONT + (rows - rows / 2) * seats * PRICE_BACK
    }
    println("Total income: $$totalIncome")
}

fun buyTicket(cinema: MutableList<MutableList<Char>>, rows: Int, seats: Int, totalSeats: Int) {
    println("Enter a row number:")
    val x = readln().toInt()
    println("Enter a seat number in that row:")
    val y = readln().toInt()
    if (x < 1 || x > rows || y < 1 || y > seats) {
        println("Wrong input!")
        buyTicket(cinema, rows, seats, totalSeats)
    } else if (cinema[x - 1][y - 1] == 'B') {
        println("That ticket has already been purchased!")
        buyTicket(cinema, rows, seats, totalSeats)
    } else {
    val frontRows = rows / 2
    var ticketPrice = PRICE_FRONT
    if (totalSeats > 60 && x > frontRows) ticketPrice = PRICE_BACK
    println("\nTicket price:\n$$ticketPrice")  
    cinema[x - 1][y - 1] = 'B'  
    countSold++
    currentIncome += ticketPrice
    }     
}

fun printCinema(cinema: MutableList<MutableList<Char>>, seats: Int) {
    println("\nCinema:")
    for (i in 1..seats) {
        print(" ${ i}")  
    } 
    println()
    for (i in cinema.indices) {
        println("${i + 1} ${cinema[i].joinToString(" ")}")
    }
    println()
}
