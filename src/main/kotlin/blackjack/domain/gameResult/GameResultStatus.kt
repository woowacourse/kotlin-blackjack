package blackjack.domain.gameResult

enum class GameResultStatus {
    PLAYER_WIN {
        override fun earn(bettingAmount: Int): Int = 1 * bettingAmount
    },
    PLAYER_LOSE {
        override fun earn(bettingAmount: Int): Int = -1 * bettingAmount
    },
    DRAW {
        override fun earn(bettingAmount: Int): Int = 0
    },
    PLAYER_BLACKJACK {
        override fun earn(bettingAmount: Int): Int = (1.5 * bettingAmount).toInt()
    },
    DEALER_BLACKJACK {
        override fun earn(bettingAmount: Int): Int = -1 * bettingAmount
    }, ;

    abstract fun earn(bettingAmount: Int): Int
}
