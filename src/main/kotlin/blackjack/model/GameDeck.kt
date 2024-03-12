package blackjack.model

import blackjack.exception.ErrorCode
import blackjack.exception.Exceptions

object GameDeck : ShuffleGenerator {
    private const val CARD_DRAW_DEFAULT_INDEX = 0

    private val deck: List<Card> = createGameDeck()
    private val userDeck: MutableList<Card> = mutableListOf()
    private var index: Int = CARD_DRAW_DEFAULT_INDEX

    override fun resetUserDeck(cards: List<Card>?) {
        userDeck.clear()
        index = CARD_DRAW_DEFAULT_INDEX
        cards?.let { userDeck.addAll(it) } ?: { userDeck.addAll(deck.shuffled()) }
    }

    fun drawCard(): Card {
        if (index < userDeck.size) {
            return userDeck[index++]
        }
        resetUserDeck()
        throw Exceptions.NoCardErrorException(ErrorCode.NO_CARDS_ERROR.reason)
    }

    private fun createGameDeck(): List<Card> = Card.createDeck()
}
