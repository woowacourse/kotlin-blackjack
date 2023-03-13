package model

class Player private constructor(
    cards: Cards,
    name: Name,
    private val money: Money,
    private val needToDraw: (String) -> Boolean
) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isHit(): Boolean {
        return !isBust() && needToDraw(name.value)
    }

    override fun getProfitMoney(other: Participant): Profit {
        val playerCardsSum = cards.sum()
        val otherCardsSum = other.cards.sum()
        if (isBlackJack() && other.isBlackJack()) return Profit.of(money, EarningRate.DRAW)
        if (isBlackJack()) return Profit.of(money, EarningRate.BLACKJACK)
        if (isBust()) return Profit.of(money, EarningRate.LOSE)
        if (other.isBust()) return Profit.of(money, EarningRate.WIN)
        if (playerCardsSum > otherCardsSum) return Profit.of(money, EarningRate.WIN)
        if (playerCardsSum == otherCardsSum) return Profit.of(money, EarningRate.DRAW)
        return Profit.of(money, EarningRate.LOSE)
    }
    override fun isDealer(): Boolean = false

    override fun getResult(participants: Participants): Result {
        return Result(this, getProfitMoney(participants.dealer))
    }

    companion object {
        fun of(cards: Cards, name: String, money: Long, needToDraw: (String) -> Boolean = { false }): Player = Player(cards, Name(name), Money(money), needToDraw)
    }
}
