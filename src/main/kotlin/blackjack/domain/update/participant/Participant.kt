package blackjack.domain.update.participant

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.update.state.Finished
import blackjack.domain.update.state.Hittable
import blackjack.domain.update.state.NewParticipantState
import blackjack.domain.update.state.Playing
import blackjack.domain.update.state.Ready

abstract class Participant(
    private val deck: Deck,
) {
    var state: NewParticipantState = Ready(deck.take())
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
