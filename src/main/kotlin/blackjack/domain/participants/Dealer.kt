package blackjack.domain.participants

import blackjack.domain.betting.BettingInfo
import blackjack.domain.betting.ProfitAmount
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class Dealer(
    private val deck: Deck = Deck.createDefaultDeck(),
    initialHand: List<Card> = emptyList(),
) : Participant(initialHand) {
    override fun canHit(): Boolean = score <= DRAW_SCORE

    override fun visibleCard(isFirstTurn: Boolean): List<Card> {
        if (isFirstTurn) {
            return hand.cards.take(1)
        }
        return hand.cards
    }

    fun handOut(participant: Participant) {
        val card = drawFromDeck()
        participant.addCard(card)
    }

    fun calculatePlayersProfit(bettingInfo: BettingInfo): ProfitAmount {
        val result = hand.determineResult(bettingInfo.player.hand)
        return bettingInfo.calculateProfitAmount(result)
    }

    private fun drawFromDeck(): Card = deck.draw()

    companion object {
        private const val DRAW_SCORE = 16
    }
}
