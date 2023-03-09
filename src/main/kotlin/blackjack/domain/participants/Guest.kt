package blackjack.domain.participants

class Guest(name: String, bettingMoney: Int = 10) : User(name) {
    val bettingMoney: Money = Money(bettingMoney)

    override val isContinuable: Boolean
        get() = isBust.not() && isBlackJack.not()
}
