package blackjack.model

class DealerStrengthPolicy : StrengthPolicy() {
    override val bustedStrength: Int = BUSTED_STRENGTH
    companion object {
        private const val BUSTED_STRENGTH = 0
    }
}
