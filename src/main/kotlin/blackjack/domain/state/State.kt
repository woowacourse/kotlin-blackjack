package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.participants.user.Dealer
import blackjack.domain.result.Outcome

interface State {
    val cards: Cards
    val score: Score
    val size: Int

    fun draw(card: Card): State

    fun matchWith(dealer: Dealer): Outcome
}
