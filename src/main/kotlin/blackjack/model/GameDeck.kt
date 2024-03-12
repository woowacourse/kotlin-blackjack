package blackjack.model

import blackjack.exception.ErrorCode
import blackjack.exception.Exceptions

object GameDeck : ShuffleGenerator {
    private const val CARD_DRAW_DEFAULT_INDEX = 0

    private val deck: List<Card> = createGameDeck()
    private val userDeck: MutableList<Card> = mutableListOf()
    private var index: Int = CARD_DRAW_DEFAULT_INDEX

    override fun shuffleGameDeck() {
        userDeck.clear()
        userDeck.addAll(deck.shuffled())
        index = CARD_DRAW_DEFAULT_INDEX
    }

    override fun shuffleGameDeck(cards: List<Card>) {
        userDeck.clear()
        userDeck.addAll(cards)
        index = CARD_DRAW_DEFAULT_INDEX
    }

    fun drawCard(): Card {
        if (index < userDeck.size) {
            return userDeck[index++]
        }
        shuffleGameDeck()
        throw Exceptions.NoCardErrorException(ErrorCode.NO_CARDS_ERROR.reason)
    }

    private fun createGameDeck(): List<Card> = Card.createDeck()
}
