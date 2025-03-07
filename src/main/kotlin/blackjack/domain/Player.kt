package blackjack.domain

class Player(val name: String) : Participant() {
    override val hitThreshold: Int
        get() = PLAYER_HIT_THRESHOLD

    companion object {
        const val PLAYER_HIT_THRESHOLD = 21
    }
}
