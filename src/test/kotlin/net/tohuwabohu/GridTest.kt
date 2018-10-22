package net.tohuwabohu

import kotlin.test.Test

class GridTest {

    @Test
    fun createGrid() {
        Grid( 2)
    }

    @Test
    fun updateCell() {
        Grid(1).update(Cell(0,0), CellState.Alive)
    }

    @Test
    fun swapGrid() {
        Grid(1).swap()
    }
}