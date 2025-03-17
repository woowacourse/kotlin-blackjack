package blackjack.domain.participants

import blackjack.domain.betting.BettingAmount
import blackjack.domain.betting.BettingInfo
import blackjack.domain.betting.ProfitAmount
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.ResultState

class Dealer(
    private val deck: Deck = Deck.createDefaultDeck(),
    initialHand: List<Card> = emptyList(),
) : Participant(initialHand) {
    override fun canHit(): Boolean = score <= DRAW_SCORE

    fun handOut(participant: Participant) {
        val card = drawFromDeck()
        participant.addCard(card)
    }

    fun profitFromGame(bettingInfo: BettingInfo): ProfitAmount {
        val result = determineResult(bettingInfo.player)
        return calculateProfit(bettingInfo.bettingAmount, result)
    }

    private fun determineResult(player: Player): ResultState {
        return when {
            player.isBlackjack() -> ResultState.BLACKJACK_WIN
            player.isBust() -> ResultState.LOSE
            this.isBust() -> ResultState.WIN
            player.score > this.score -> ResultState.WIN
            player.score < this.score -> ResultState.LOSE
            else -> ResultState.DRAW
        }
    }

    private fun calculateProfit(
        bettingAmount: BettingAmount,
        resultState: ResultState,
    ): ProfitAmount {
        val profit = bettingAmount.value * resultState.profitRate
        return ProfitAmount(profit)
    }

    private fun drawFromDeck(): Card = deck.draw()

    companion object {
        private const val DRAW_SCORE = 16
    }
}
