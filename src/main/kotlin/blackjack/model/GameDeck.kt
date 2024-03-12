package blackjack.model

import blackjack.exception.ErrorCode
import blackjack.exception.Exceptions

object GameDeck : ShuffleGenerator {
    private const val CARD_DRAW_DEFAULT_INDEX = 0

    private val deck: List<Card> = createGameDeck()
    private val currentDeck: MutableList<Card> = mutableListOf()
    private var index: Int = CARD_DRAW_DEFAULT_INDEX

    override fun resetCurrentDeck(cards: List<Card>?) {
        currentDeck.clear()
        index = CARD_DRAW_DEFAULT_INDEX
        cards?.let { currentDeck.addAll(it) } ?: run { currentDeck.addAll(deck.shuffled()) }
    }

    fun drawCard(): Card {
        if (index < currentDeck.size) {
            return currentDeck[index++]
        }
        resetCurrentDeck()
        throw Exceptions.NoCardErrorException(ErrorCode.NO_CARDS_ERROR.reason)
    }

    private fun createGameDeck(): List<Card> = Card.createDeck()
}
