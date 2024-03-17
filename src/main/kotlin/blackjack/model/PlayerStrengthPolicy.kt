package blackjack.model

class PlayerStrengthPolicy : StrengthPolicy() {
    override val bustedStrength: Int = BUSTED_STRENGTH

    companion object {
        private const val BUSTED_STRENGTH = -1
    }
}
