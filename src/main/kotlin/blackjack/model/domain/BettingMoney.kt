package blackjack.model.domain

@JvmInline
value class BettingMoney(val amount: Float) {
    val blackjackMoney get() = amount * 1.5f
    val loseMoney get() = amount * -1f
    val winMoney get() = amount * 1f
    val drawMoney get() = 0f
}
