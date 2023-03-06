package blackjack.domain.participants

class Guest(name: String, bettingMoney: Int = 10) : User(name) {
    val bettingMoney: Money = Money(bettingMoney)

    override fun isContinue(): Boolean = isBust().not() && isBlackJack().not()
}
