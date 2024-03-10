package blackjack.model

sealed class Participant(val name: ParticipantName, state: State) {
    var state = state
        private set

    fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun finishRound() {
        state = state.stay()
    }

    fun getCards(): List<Card> = state.hand().cards
}

class Player(name: ParticipantName, state: State) : Participant(name, state)

class Dealer(name: ParticipantName = ParticipantName(DEALER_NAME), state: State) : Participant(name, state) {
    fun isUnderHitThreshold(threshold: Int = THRESHOLD_HIT): Boolean = state.hand().sumUpCardValues() <= threshold

    companion object {
        const val THRESHOLD_HIT = 16
        const val DEALER_NAME = "딜러"
    }
}
