package blackjack.model

class Dealer(name: ParticipantName = ParticipantName("딜러"), state: State) : Participant(name, state) {
    fun isUnderHitThreshold(threshold: Int = THRESHOLD_HIT): Boolean = state.hand().calculateSum() <= threshold

    companion object {
        const val THRESHOLD_HIT = 16
    }
}
