package domain.result

import domain.card.strategy.SumStrategy.getAppropriateSum
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
        fun getOutCome(dealer: Dealer, player: Player): Pair<String, OutCome> {
            val gap =
                player.getTotalCardNumber { getAppropriateSum() } - dealer.getTotalCardNumber { getAppropriateSum() }
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
