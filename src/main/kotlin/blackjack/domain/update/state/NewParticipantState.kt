package blackjack.domain.update.state

import blackjack.domain.Card
import blackjack.domain.update.NewHand
import blackjack.domain.update.NewScore

sealed class NewParticipantState {
    abstract val hand: NewHand
    val cards: List<Card> get() = hand.cards
    val score: NewScore get() = hand.score
}
