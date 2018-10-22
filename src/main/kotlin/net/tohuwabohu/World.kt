package net.tohuwabohu

class World(private val grid: Grid) {

    fun tick() {
        grid.forEach { cell: Cell, cellState: CellState ->
            val aliveNeighbours = grid.countLiveNeighbours(cell)
            when (cellState) {
                CellState.Alive -> {
                    when (aliveNeighbours) {
                        0, 1 -> grid.update(cell, CellState.Dead)
                        2, 3 -> grid.update(cell, cellState)
                        else -> grid.update(cell, CellState.Dead)
                    }
                }
                CellState.Dead -> {
                    grid.update(cell, if (aliveNeighbours == 3) CellState.Alive else CellState.Dead )
                }
            }
        }
        grid.swap()
    }
}