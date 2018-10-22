package net.tohuwabohu

import org.assertj.core.api.Assertions
import kotlin.test.Test

class WorldTest {

    @Test
    fun block() {
        val grid = Grid(Dimension(4))
        grid.update(Cell(1,1), CellState.Alive)
        grid.update(Cell(1,2), CellState.Alive)
        grid.update(Cell(2,1), CellState.Alive)
        grid.update(Cell(2,2), CellState.Alive)
        grid.swap()
        val stateBefore = grid.externalize()

        World(grid).tick()

        Assertions.assertThat(grid.externalize()).isEqualTo(stateBefore)
    }

    @Test
    fun blinker() {
        val grid = Grid(Dimension(5))
        grid.update(Cell(2,1), CellState.Alive)
        grid.update(Cell(2,2), CellState.Alive)
        grid.update(Cell(2,3), CellState.Alive)
        grid.swap()

        val world = World(grid)

        val iteration0 = grid.externalize()
        world.tick()
        world.tick()
        val iteration2 = grid.externalize()

        Assertions.assertThat(iteration0).isEqualTo(iteration2)
    }
}