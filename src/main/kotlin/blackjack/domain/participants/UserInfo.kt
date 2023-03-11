package blackjack.domain.participants

data class UserInfo(
    val name: Name,
    val money: Money,
) {
    constructor(name: String, money: Int) : this(Name(name), Money(money))
}
