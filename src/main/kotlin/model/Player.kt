package model

class Player private constructor(name: Name, private val money: Money, private val needToDraw: (String) -> Boolean) : Participant(name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isPossibleDrawCard(): Boolean = !isBust()

    override fun isHit(): Boolean {
        return isPossibleDrawCard() && needToDraw(name.value)
    }

    override fun getProfitMoney(other: Participant): Profit {
        val playerCardsSum = cards.sum()
        val otherCardsSum = other.cards.sum()
        if (isBlackJack() && other.isBlackJack()) return Profit.of(money, EarningRate.WIN_OR_BLACKJACK_DRAW)
        if (isBlackJack()) return Profit.of(money, EarningRate.BLACKJACK)
        if (isBust()) return Profit.of(money, EarningRate.LOSE)
        if (other.isBust()) return Profit.of(money, EarningRate.WIN_OR_BLACKJACK_DRAW)
        if (playerCardsSum > otherCardsSum) return Profit.of(money, EarningRate.WIN_OR_BLACKJACK_DRAW)
        if (playerCardsSum == otherCardsSum) return Profit.of(money, EarningRate.DRAW)
        return Profit.of(money, EarningRate.LOSE)
    }
    override fun isDealer(): Boolean = false

    companion object {
        fun of(name: String, money: Long, needToDraw: (String) -> Boolean = { false }): Player = Player(Name(name), Money(money), needToDraw)
    }
}
