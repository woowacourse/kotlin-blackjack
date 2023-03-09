package blackjack.domain.participants

class Guest(name: String, val bettingMoney: Money = Money(10)) : User(name) {
    override val isContinuable: Boolean
        get() = isBust.not() && isBlackJack.not()
}
