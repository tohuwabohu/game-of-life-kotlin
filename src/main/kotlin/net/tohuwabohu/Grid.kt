package net.tohuwabohu

import java.util.Arrays


data class Grid(private val state: Array<Array<CellState>>) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        if (!Arrays.equals(state, other.state)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(state)
    }
}