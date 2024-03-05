package blackjack.model

class Dealer : Role() {
    override fun isBurst(): Boolean {
        return cardSum >= BURST_CONDITION
    }

    companion object {
        private const val BURST_CONDITION = 17
    }
}
