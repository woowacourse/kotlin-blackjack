package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.constant.BIG_ACE
import domain.constant.BLACK_JACK
import domain.constant.NOTHING
import domain.constant.SMALL_ACE

abstract class Person {
    abstract val name: String
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()
    var gameState: GameState = GameState.HIT
        protected set

    fun receiveCard(card: Card) {
        _cards.add(card)
        checkState()
    }

    protected abstract fun checkState()

    fun calculateTotalCardNumber(): Int {
        val sumExceptAce: Int = calculateSumExceptAce()
        val aceCount: Int = countAce()
        return sumExceptAce + calculateSumAce(BLACK_JACK - sumExceptAce, aceCount)
    }

    protected fun countAce() = cards.count { it.number == CardNumber.ACE }
    protected fun calculateSumExceptAce() = cards
        .filter { it.number != CardNumber.ACE }
        .sumOf { it.number.value }

    private fun calculateSumAce(availableMax: Int, aceCount: Int) = when {
        aceCount == NOTHING -> NOTHING
        availableMax >= BIG_ACE + aceCount - 1 -> BIG_ACE + (aceCount - 1) * SMALL_ACE
        else -> aceCount * SMALL_ACE
    }
}
