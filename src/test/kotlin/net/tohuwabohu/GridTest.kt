package net.tohuwabohu

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class GridTest {

    @Test
    fun createGrid() {
        Grid( Dimension(2))
    }

    @Test
    fun updateCell() {
        Grid(Dimension(1)).update(Cell(0,0), CellState.Alive)
    }

    @Test
    fun swapGrid() {
        Grid(Dimension(1)).swap()
    }

    @Test
    fun countNeightbour() {
        val dimension = Dimension(3)
        val center = Cell(1, 1)
        arrayOf(0, 1, 2, 3, 5, 6, 7, 8).forEach {
            val grid = Grid(dimension)
            grid.update(dimension.cellFromIndex(it), CellState.Alive)
            grid.swap()
            assertThat(grid.countLiveNeighbours(center)).isEqualTo(1)
        }
    }
}