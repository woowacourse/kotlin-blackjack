package blackjack.model

class Dealer(name: String = "딜러", state: State) : Participant(name, state) {
    fun isUnderHitThreshold(threshold: Int = THRESHOLD_HIT): Boolean = state.hand().calculateSum() <= threshold

    companion object {
        const val THRESHOLD_HIT = 16
    }
}
