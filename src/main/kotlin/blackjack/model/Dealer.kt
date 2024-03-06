package blackjack.model

class Dealer : Role() {
    override val burstCondition = BURST_CONDITION

    companion object {
        private const val BURST_CONDITION = 17
    }
}
