package blackjack.domain.participant

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.state.Finished
import blackjack.domain.state.Hittable
import blackjack.domain.state.ParticipantState
import blackjack.domain.state.Playing
import blackjack.domain.state.Ready

abstract class Participant(
    private val deck: Deck,
) {
    var state: ParticipantState = Ready(deck.take())
        private set
    val cards get() = state.cards
    val score get() = state.score
    val isFinished get() = state is Finished

    fun hit(card: Card = deck.take()) {
        state = (state as? Hittable)?.hit(card) ?: throw IllegalStateException("Player is not hittable")
    }

    fun stay() {
        state = (state as? Playing)?.stay() ?: throw IllegalStateException("Player is not playing")
    }
}
