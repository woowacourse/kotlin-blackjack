package blackjack.domain

data class BetMoney(private val _money: Double) {
    val money: Int
        get() = _money.toInt()

    init {
        require(_money > 0.0) { "베팅하는 금액은 0이거나 음수일 수 없습니다. (잘못된 값: $money)" }
        require(_money % 1000 == 0.0) { "베팅하는 금액은 1000원 단위여야 합니다. (잘못된 값: $money)" }
    }

    constructor(money: Int) : this(money.toDouble())

    fun addBlackjackPrizeMoney() = (_money * 1.5).toInt()
}
