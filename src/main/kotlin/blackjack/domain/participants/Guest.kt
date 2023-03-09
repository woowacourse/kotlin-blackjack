package blackjack.domain.participants

class Guest(name: Name, val bettingMoney: Money = Money(10)) : User(name) {
    override val isContinuable: Boolean
        get() = isBust.not() && isBlackJack.not()
}
