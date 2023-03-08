package domain

class BlackJackCardDeck(val deck: List<Card> = Card.DECK) : CardDeck {
    private var i = 0
        set(value) {
            field = if (i >= deck.size) {
                0
            } else {
                value
            }
        }

    init {
        check(deck.isNotEmpty()) { ERROR_DECK_EMPTY }
    }

    override fun draw(): Card {
        return deck[i++]
    }

    override fun drawInitCards(): Cards {
        return Cards(List(DRAW_INIT_CARD_COUNT) { draw() })
    }

    companion object {
        const val DRAW_INIT_CARD_COUNT = 2
        private const val ERROR_DECK_EMPTY = "[ERROR] 덱에는 최소한 카드가 한 장이상 들어있어야 합니다."
    }
}
