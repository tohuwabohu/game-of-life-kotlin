package net.tohuwabohu

import java.util.Scanner

fun main(args: Array<String>) {
    println("Hello, this is Conway's Game of Life")
    val dimension = Dimension(6)
    val grid = Grid(dimension).initialize(
            Cell(1, 1),
            Cell(1, 2),
            Cell(2, 1),
            Cell(4, 4),
            Cell(4, 3),
            Cell(3, 4)
    )
    val world = World(grid)
    val scanner = Scanner(System.`in`)
    while (true) {
        var rowIndex = 0
        println("=========================")
        grid.forEach { cell, cellState ->
            if (cell.x > rowIndex) {
                rowIndex++
                println()
            }
            printCell(cellState)
        }
        println("\n=========================\n")
        if (scanner.next() == "c") break
        world.tick()
    }
    println("\n\nBye.")
}

private fun printCell(cellState: CellState) {
    print(if (cellState == CellState.Alive) "x" else " ")
}

