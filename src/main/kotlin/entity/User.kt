package entity

interface User {
    fun isDistributable(): Boolean

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
    }
}
