package blackjack.model

@JvmInline
value class Betting(val amount: Int) {
    init {
        require(amount >= MIN_BETTING_AMOUNT) {"Betting 의 최소 금액은 $MIN_BETTING_AMOUNT 다"}
        require(amount % BETTING_UNIT == 0) { "$amount - Betting 금액의 단위는 $BETTING_UNIT 다" }
    }

    companion object {
        private const val MIN_BETTING_AMOUNT = 0
        private const val BETTING_UNIT = 10_000
    }
}
