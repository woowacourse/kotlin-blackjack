package blackjack.model

sealed class Participant(val name: ParticipantName, var state: State) {
    fun calculateHandSum() = state.hand.calculateSum()

    fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun finishRound() {
        state = state.stay()
    }

    fun getCards(): List<Card> {
        return state.hand.cards
    }

    fun isRunning() = state is Running
}

class Dealer(name: ParticipantName = ParticipantName(ParticipantName.DEALER_NAME), state: State) :
    Participant(name, state) {
    fun isUnderHitThreshold(threshold: Int = State.THRESHOLD_HIT_FOR_DEALER): Boolean = calculateHandSum() <= threshold
}

class Player(name: ParticipantName, state: State) : Participant(name, state)
