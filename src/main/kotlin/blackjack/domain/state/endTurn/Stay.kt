package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.participants.user.Dealer
import blackjack.domain.result.Outcome
import blackjack.domain.state.inTurn.Hit

class Stay(cards: Cards) : EndTurn(cards) {
    override fun matchWith(dealer: Dealer): Outcome =
        when (dealer.state) {
            is BlackJack -> Outcome.DRAW
            is Bust -> Outcome.WIN
            is Stay -> compareScore(dealer)
            is Hit -> compareScore(dealer)
            else -> {
                throw IllegalStateException("Dealer's state is not valid") }
        }

    private fun compareScore(dealer: Dealer) = when {
        dealer.score > score -> Outcome.LOSE
        dealer.score < score -> Outcome.WIN
        else -> Outcome.DRAW
    }
}
