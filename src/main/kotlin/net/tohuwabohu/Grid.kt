package net.tohuwabohu

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicReference


class Grid(
        private val dimension: Dimension
) {
    private val current: AtomicReference<Array<CellState>> = AtomicReference(Array(dimension.siz()) { CellState.Dead })
    private val next: AtomicReference<Array<CellState>> = AtomicReference(Array(dimension.siz()) { CellState.Dead })

    companion object {
        val log: Logger = LoggerFactory.getLogger(Grid::class.java)
    }

    fun forEach(action: (Cell, CellState) -> Unit) {
        current.get().forEachIndexed { index: Int, cellState: CellState ->
            action.invoke(dimension.cellFromIndex(index), cellState)
        }
    }

    fun countLiveNeighbours(cell: Cell): Int {
        val index = dimension.indexFromCell(cell)
        return arrayOf(
                index - dimension.value - 1,    // top left
                index - dimension.value,        // top
                index - dimension.value + 1,    // top right
                index - 1,                      // left
                index + 1,                      // right
                index + dimension.value - 1,    // bottom left
                index + dimension.value,        // bottom
                index + dimension.value + 1     // bottom right
        ).count {
            // TODO: handle out of bounds
            // TODO: handle wrap around
            current.get()[it] == CellState.Alive
        }
    }

    fun swap() {
        log.debug("Swapping the grid...")
        val active = current.get()
        current.set(next.get())
        next.set(active)
        log.info("Swapped the grid; new active is $current, next is $next")
    }

    fun update(cell: Cell, state: CellState) {
        log.debug("Updating status for cell $cell to $state")
        val index = dimension.indexFromCell(cell)
        if (next.get().size <= index) {
            throw IllegalArgumentException("Cell $cell is out of range for this grid with length ${current.get().size} and dimension ${dimension.value}")
        }
        next.get()[index] = state
        log.info("Updated status for cell $cell to $state")
    }
}