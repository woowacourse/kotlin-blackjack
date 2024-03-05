package blackjack.model

import java.lang.IllegalStateException

class GameDeck(cards: List<Card> = listOf()) {
    private var _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards

    init {
        _cards = initDeck()
        shuffleCards()
    }

    private fun initDeck(): MutableList<Card> {
        val newCards = mutableListOf<Card>()

        Pattern.entries.forEach { pattern ->
            CardNumber.entries.forEach { number ->
                newCards.add(Card(pattern = pattern, number = number))
            }
        }
        return newCards
    }

    private fun shuffleCards() {
        _cards.shuffle()
    }

    fun getCard(): Card {
        if (_cards.isNotEmpty()) {
            return _cards.removeFirst()
        }
        throw IllegalStateException()
    }
}
