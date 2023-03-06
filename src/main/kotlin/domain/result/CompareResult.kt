package domain.result

import domain.card.strategy.GetAppropriateSum
import domain.person.Dealer
import domain.person.Player

sealed class CompareResult {
    abstract fun result(playerName: String): Pair<String, OutCome>

    object PlayerBust : CompareResult() {
        override fun result(playerName: String) = playerName to OutCome.LOSE
    }

    object DealerBust : CompareResult() {
        override fun result(playerName: String) = playerName to OutCome.WIN
    }

    object PlayerBigger : CompareResult() {
        override fun result(playerName: String) = playerName to OutCome.WIN
    }

    object DealerBigger : CompareResult() {
        override fun result(playerName: String) = playerName to OutCome.LOSE
    }

    object Same : CompareResult() {
        override fun result(playerName: String) = playerName to OutCome.DRAW
    }

    companion object {
        private val sumStrategy = GetAppropriateSum()

        fun compare(dealer: Dealer, player: Player): CompareResult {
            val gap = player.getTotalCardNumber(sumStrategy) - dealer.getTotalCardNumber(sumStrategy)

            return when {
                player.isBust() -> PlayerBust
                dealer.isBust() -> DealerBust
                gap > 0 -> PlayerBigger
                gap < 0 -> DealerBigger
                else -> Same
            }
        }
    }
}
