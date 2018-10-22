package net.tohuwabohu

data class Dimension(val value: Int) {

    fun cellFromIndex(index: Int): Cell {
        val x = index / value
        val y = index % value
        return Cell(x, y)
    }

    fun indexFromCell(cell: Cell): Int {
        return cell.x * value + cell.y
    }

    fun siz(): Int {
        return value * value
    }
}