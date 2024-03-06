package blackjack.model

import java.lang.IllegalStateException

class GameDeck {
    private var _cards: List<Card> = initDeck().shuffled()
    val cards: List<Card>
        get() = _cards

    private fun initDeck(): List<Card> {
        val newCards = mutableListOf<Card>()

        Pattern.entries.forEach { pattern ->
            CardNumber.entries.forEach { number ->
                newCards.add(Card(pattern = pattern, number = number))
            }
        }
        return newCards
    }

    fun reset() {
        _cards = initDeck().shuffled()
    }

    fun drawCard(): Card {
        if (_cards.isNotEmpty()) {
            return cards.last().also {
                _cards = _cards.dropLast(1)
            }
        }
        throw IllegalStateException()
    }
}
