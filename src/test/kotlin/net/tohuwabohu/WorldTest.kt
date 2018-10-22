package net.tohuwabohu

import org.assertj.core.api.Assertions
import kotlin.test.Test

class WorldTest {

    @Test
    fun block() {
        val grid = Grid(Dimension(4)).initialize(
                Cell(1,1),
                Cell(1,2),
                Cell(2,1),
                Cell(2,2)
        )

        val stateBefore = grid.externalize()

        World(grid).tick()

        Assertions.assertThat(grid.externalize()).isEqualTo(stateBefore)
    }

    @Test
    fun blinker() {
        val grid = Grid(Dimension(5)).initialize(
                Cell(2,1),
                Cell(2,2),
                Cell(2,3)
        )

        val world = World(grid)

        val iteration0 = grid.externalize()
        world.tick()
        world.tick()
        val iteration2 = grid.externalize()

        Assertions.assertThat(iteration0).isEqualTo(iteration2)
    }
}