package entity

interface User {
    fun isDistributable(): Boolean

    companion object {
        const val SINGLE_DISTRIBUTE_COUNT = 1
        const val BLACKJACK_COUNT = 2
    }
}
