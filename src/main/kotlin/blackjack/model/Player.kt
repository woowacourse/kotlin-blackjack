package blackjack.model

class Player(val name: PlayerName) : Role() {
    override val burstCondition = BURST_CONDITION

    companion object {
        private const val BURST_CONDITION = 21
    }
}
