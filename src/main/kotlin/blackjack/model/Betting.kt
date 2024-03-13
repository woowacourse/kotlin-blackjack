package blackjack.model

@JvmInline
value class Betting(val amount: Int) {
    init {
        require(amount >= MIN_BETTING_AMOUNT) { "$amount - $MIN_BETTING_AMOUNT 이상부터 Betting 이 가능하다" }
        require(amount % BETTING_UNIT == 0) { "$amount - Betting 금액의 단위는 $BETTING_UNIT 다" }
    }

    operator fun times(rate: Double): Betting = Betting((amount * rate).toInt())

    companion object {
        private const val MIN_BETTING_AMOUNT = 10_000
        private const val BETTING_UNIT = 10_000
    }
}
