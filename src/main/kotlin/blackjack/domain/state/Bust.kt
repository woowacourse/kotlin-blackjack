package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.participants.Dealer
import blackjack.domain.result.Outcome

class Bust(cards: Cards) : EndTurn(cards) {
    override fun matchWith(dealer: Dealer): Outcome {
        return when (dealer.state) {
            is Bust -> Outcome.DRAW
            else -> Outcome.LOSE
        }
    }
}
