package domain.result

import domain.card.strategy.GetAppropriateSum
import domain.person.Dealer
import domain.person.Player

enum class OutCome {
    WIN {
        override fun convertOutCome() = LOSE
    },
    DRAW {
        override fun convertOutCome() = DRAW
    },
    LOSE {
        override fun convertOutCome() = WIN
    }, ;

    abstract fun convertOutCome(): OutCome

    companion object {
        private val sumStrategy = GetAppropriateSum

        fun getOutCome(dealer: Dealer, player: Player): Pair<String, OutCome> {
            val gap =
                player.getTotalCardNumber(sumStrategy) - dealer.getTotalCardNumber(sumStrategy)
            return when {
                player.isBust() -> player.name to LOSE
                dealer.isBust() -> player.name to WIN
                gap > 0 -> player.name to WIN
                gap < 0 -> player.name to LOSE
                else -> player.name to DRAW
            }
        }
    }
}
