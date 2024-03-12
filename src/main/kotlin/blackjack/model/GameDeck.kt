package blackjack.model

object GameDeck : ShuffleGenerator {
    private const val NO_CARDS_ERROR_MESSAGE = "카드 덱에 카드가 없습니다. 카드를 다시 섞겠습니다."
    private const val CARD_DRAW_DEFAULT_INDEX = 0

    private val deck: List<Card> = createGameDeck()
    private val currentDeck: MutableList<Card> = deck.shuffled().toMutableList()
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
        throw IllegalStateException(NO_CARDS_ERROR_MESSAGE)
    }

    private fun createGameDeck(): List<Card> = Card.createDeck()
}
