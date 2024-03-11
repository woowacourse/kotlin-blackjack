package blackjack.model

import blackjack.exception.ErrorCode.*
import blackjack.exception.Exceptions.NoCardErrorException

class GameDeck(initCards: List<Card> = ShuffleGeneratorImpl.shuffleGameDeck()) {
    private var _cards: List<Card> = initCards
    val cards: List<Card>
        get() = _cards

    fun reset(newCards: List<Card>? = null) {
        _cards = newCards?.let {
            ShuffleGeneratorImpl.shuffleGameDeck(it)
        } ?: ShuffleGeneratorImpl.shuffleGameDeck()
    }

    fun drawCard(): Card {
        if (_cards.isNotEmpty()) {
            return cards.last().also {
                _cards = _cards.dropLast(DRAW_COUNT)
            }
        }
        throw NoCardErrorException(NO_CARDS_ERROR.reason)
    }

    companion object {
        const val DECK_SIZE = 52
        const val DRAW_COUNT = 1
    }
}
