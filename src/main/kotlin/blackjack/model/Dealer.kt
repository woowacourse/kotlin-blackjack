package blackjack.model

import blackjack.model.CardsStatus.Companion.BUST_SCORE

class Dealer(
    name: String = "딜러",
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    val openCard: Card
        get() = hand.value[OPEN_CARD_INDEX]
    val profit: Money = Money(INITIAL_MONEY_VALUE)

    fun getResult(playerScore: Int): GameResult {
        if (playerScore == BUST_SCORE) return GameResult.WIN
        if (isBust()) return GameResult.LOSE
        return calculateResult(playerScore)
    }

    private fun calculateResult(playerScore: Int): GameResult = GameResult.of(getScore(), playerScore)

    override fun canHit(): Boolean = getScore() <= DEALER_HIT_SCORE

    override fun gainMoney(money: Money) {
        profit.plus(money)
    }

    override fun lossMoney(money: Money) {
        profit.minus(money)
    }

    companion object {
        private const val OPEN_CARD_INDEX = 0
        private const val INITIAL_MONEY_VALUE = 0.0
        private const val DEALER_HIT_SCORE = 16
    }
}
