package blackjack.domain.model.betting

@JvmInline
value class BetAmount(
    val value: Double,
) {
    init {
        require(value >= 0) { ERROR_SHOULD_BE_MORE_THAN_ZERO }
    }

    companion object {
        const val ERROR_SHOULD_BE_MORE_THAN_ZERO = "베팅 금액은 0원 이상이어야 합니다."
    }
}
