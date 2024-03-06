package blackjack.model

class Dealer : Role() {
    override val burstCondition = BURST_CONDITION
    val result = DealerResult()

    companion object {
        private const val BURST_CONDITION = 17
    }
}
