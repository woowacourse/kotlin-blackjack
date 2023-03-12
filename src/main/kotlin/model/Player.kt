package model

class Player(name: Name, private val money: Money) : Participant(name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isPossibleDrawCard(): Boolean = !isBust()

    override fun isHit(needToDraw: (String) -> Boolean): Boolean {
        return isPossibleDrawCard() && needToDraw(name.value)
    }

    override fun getProfitMoney(other: Participant): Profit {
        if (isBlackJack() && other.isBlackJack()) return Profit.of(money, CardGameResult.DRAW)
        if (isBlackJack()) return Profit.of(money, CardGameResult.BLACKJACK)
        if (isBust()) return Profit.of(money, CardGameResult.LOSE)
        if (other.isBust()) return Profit.of(money, CardGameResult.WIN)
        if (cards.sum() > other.cards.sum()) return Profit.of(money, CardGameResult.WIN)
        if (cards.sum() == other.cards.sum()) return Profit.of(money, CardGameResult.DRAW)
        return Profit.of(money, CardGameResult.LOSE)
    }

    companion object {
        fun of(name: Name, money: Long): Player = Player(name, Money(money))
    }
}
