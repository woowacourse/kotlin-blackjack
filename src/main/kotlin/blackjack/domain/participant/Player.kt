package blackjack.domain.participant

class Player(val name: String, val bettingAmount: Int = 0) : Participant() {
    override val hitThreshold: Int
        get() = PLAYER_HIT_THRESHOLD

    companion object {
        const val PLAYER_HIT_THRESHOLD = 21
    }
}
