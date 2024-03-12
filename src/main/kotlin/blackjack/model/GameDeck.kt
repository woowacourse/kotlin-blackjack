package blackjack.model

import java.lang.IllegalStateException

class GameDeck {
    private var _cards: List<Card> = initDeck()
    val cards: List<Card>
        get() = _cards

    fun reset() {
        _cards = initDeck().shuffled()
    }

    fun drawCard(): Card {
        if (_cards.isNotEmpty()) {
            return cards.last().also {
                _cards = _cards.dropLast(DRAW_COUNT)
            }
        }
        throw IllegalStateException("카드 덱에 카드가 없습니다")
    }

    companion object {
        const val DECK_SIZE = 52
        const val DRAW_COUNT = 1

        private val initialCards: List<Card> =
            Pattern.entries.flatMap { pattern ->
                CardNumber.entries.map { number ->
                    Card(pattern = pattern, number = number)
                }
            }

        fun initDeck(): List<Card> {
            return initialCards.shuffled()
        }
    }
}
