package blackjack.model

abstract class Participant(val name: ParticipantName, state: State) {
    var state = state
        private set

    fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun finishRound() {
        state = state.stay()
    }
}
