package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.BlackJackRule
import blackjack.domain.participant.BlackJackPair
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

data class PlayerResult(val blackJackRule: BlackJackRule<Player>, val gameResult: GameResult) {
    fun getProfit(): Int = (blackJackRule.getEarnRate(gameResult) * blackJackRule.participant.bettingAmount).toInt()

    companion object {
        fun createResultList(pair: BlackJackPair): List<PlayerResult> {
            return pair.players.map {
                judgePlayerResult(pair.dealer, it)
            }
        }

        private fun judgePlayerResult(
            dealer: Dealer,
            player: Player,
        ): PlayerResult {
            val playerState = BlackJackRule(player)
            val dealerState = BlackJackRule(dealer)
            val result = playerState.compare(dealerState)
            return PlayerResult(playerState, result)
        }
    }
}
