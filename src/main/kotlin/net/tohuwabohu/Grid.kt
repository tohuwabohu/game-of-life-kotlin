package net.tohuwabohu

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicReference


class Grid(
        private val dimension: Int
) {
    private val current: AtomicReference<Array<CellState>> = AtomicReference(Array(dimension * dimension) { CellState.Dead })
    private val next: AtomicReference<Array<CellState>> = AtomicReference(Array(dimension * dimension) { CellState.Dead })

    companion object {
        val log: Logger = LoggerFactory.getLogger(Grid::class.java)
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
        val index = cell.x * dimension + cell.y
        if (next.get().size <= index) {
            throw IllegalArgumentException("Cell $cell is out of range for this grid with length ${current.get().size} and dimension $dimension")
        }
        next.get()[index] = state
        log.info("Updated status for cell $cell to $state")
    }
}