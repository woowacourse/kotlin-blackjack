package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Score
import blackjack.domain.card.Card

sealed class ParticipantState {
    abstract val hand: Hand
    val cards: List<Card> get() = hand.cards
    val score: Score get() = hand.score
}
