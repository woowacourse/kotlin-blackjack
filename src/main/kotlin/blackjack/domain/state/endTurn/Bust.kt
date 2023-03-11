package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.participants.user.Dealer
import blackjack.domain.result.Outcome

class Bust(cards: Cards) : EndTurn(cards) {
    override fun matchWith(dealer: Dealer): Outcome =
        when (dealer.state) {
            is Bust -> Outcome.DRAW
            else -> Outcome.LOSE
        }
}
