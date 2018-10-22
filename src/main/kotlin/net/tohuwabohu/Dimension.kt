package net.tohuwabohu

data class Dimension(val value: Int) {

    fun cellFromIndex(index: Int): Cell {
        val x = index / value
        val y = index % value
        return Cell(x, y)
    }

    fun indexFromCell(cell: Cell): Int {
        val index = cell.x * value + cell.y
        if (!inRange(index)) {
            throw IllegalArgumentException("Cell $cell is out of range for this dimension ${size()}")
        }
        return index
    }

    fun inRange(index: Int): Boolean {
        return index >= 0 && index < size()
    }

    fun size(): Int {
        return value * value
    }
}