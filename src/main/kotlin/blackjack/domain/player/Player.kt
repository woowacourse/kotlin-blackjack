package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Player(
    val name: String
) {

    val cards: Cards = Cards()
    abstract val cardProvideCriteria: Int

    init {
        require(name.length in 2..10) { ERROR_NAME_LENGTH }
    }

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun calculateResult(otherSum: Int): Result {
        val mySum = cards.sumCardsNumber()
        return when {
            ((otherSum > Cards.MAX_SUM_NUMBER) and (mySum > Cards.MAX_SUM_NUMBER)) -> Result.DRAW
            (mySum > Cards.MAX_SUM_NUMBER) -> Result.LOSE
            (otherSum > Cards.MAX_SUM_NUMBER) -> Result.WIN
            (otherSum > mySum) -> Result.LOSE
            (otherSum == mySum) -> Result.DRAW
            else -> Result.WIN
        }
    }

    fun checkProvideCardPossible(): Boolean {
        if (cards.sumCardsNumber() <= cardProvideCriteria) return true
        return false
    }

    companion object {
        const val ERROR_NAME_LENGTH = "이름은 2글자 이상 10글자 이하여야 합니다."
    }
}
