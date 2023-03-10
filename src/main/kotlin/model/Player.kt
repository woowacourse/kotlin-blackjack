package model

class Player(name: Name, private val money: Money) : Participant(name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isPossibleDrawCard(): Boolean = !isBust()

    override fun isHit(needToDraw: (String) -> Boolean): Boolean {
        return isPossibleDrawCard() && needToDraw(name.value)
    }

    fun getProfitMoney(dealer: Participant): Long {
        return (getGameResult(dealer).multiple * money.value).toLong()
    }

    companion object {
        fun of(name: Name, money: Long): Player = Player(name, Money(money))
    }
}
