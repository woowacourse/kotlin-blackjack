package blackjack.model

class ParticipantBetAmount(private val amount: Int) {
    fun getAmount() = amount

    init {
        require(amount >= MIN_BET_AMOUNTS) { MINIMUM_BET_ERROR_MESSAGE }
    }

    companion object {
        private const val MIN_BET_AMOUNTS = 1
        private const val MINIMUM_BET_ERROR_MESSAGE = "배팅금액은 1원 이상이어야 합니다."
    }
}
