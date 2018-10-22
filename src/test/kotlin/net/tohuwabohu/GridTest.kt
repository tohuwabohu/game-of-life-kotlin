package net.tohuwabohu

import kotlin.test.Test

class GridTest {

    @Test
    fun createGrid() {
        Grid(arrayOf(arrayOf(CellState.Dead, CellState.Alive)))
    }
}