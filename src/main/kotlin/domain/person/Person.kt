package domain.person

import domain.card.Card
import domain.card.CardNumber

abstract class Person(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()
    var gameState: GameState = GameState.HIT

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun calculateTotalCardNumber(): Int {
        val sumExceptAce: Int = calculateSumExceptAce()
        val aceCount: Int = cards.count { it.number == CardNumber.ACE }
        return sumExceptAce + calculateSumAce(BLACK_JACK - sumExceptAce, aceCount)
    }

    private fun calculateSumExceptAce() = cards
        .filter { it.number != CardNumber.ACE }
        .sumOf { it.number.value }

    private fun calculateSumAce(availableMax: Int, aceCount: Int) = when {
        aceCount == NOTHING -> NOTHING
        availableMax >= BIG_ACE + aceCount - 1 -> BIG_ACE + (aceCount - 1) * SMALL_ACE
        else -> aceCount * SMALL_ACE
    }

    companion object {
        const val BLACK_JACK = 21
        const val NOTHING = 0
        const val BIG_ACE = 11
        const val SMALL_ACE = 1
    }
}
