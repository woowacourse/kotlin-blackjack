package blackjack.domain

class Deck {
    private val aceCards: List<Card> =
        listOf(Card(Ace(), Suit.SPADE), Card(Ace(), Suit.HEART), Card(Ace(), Suit.DIAMOND), Card(Ace(), Suit.CLOVER))
    private val spadeNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.SPADE),
            Card(Number(3), Suit.SPADE),
            Card(Number(4), Suit.SPADE),
            Card(Number(5), Suit.SPADE),
            Card(Number(6), Suit.SPADE),
            Card(Number(7), Suit.SPADE),
            Card(Number(8), Suit.SPADE),
            Card(Number(9), Suit.SPADE),
            Card(Number(10), Suit.SPADE),
        )

    private val heartNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.HEART),
            Card(Number(3), Suit.HEART),
            Card(Number(4), Suit.HEART),
            Card(Number(5), Suit.HEART),
            Card(Number(6), Suit.HEART),
            Card(Number(7), Suit.HEART),
            Card(Number(8), Suit.HEART),
            Card(Number(9), Suit.HEART),
            Card(Number(10), Suit.HEART),
        )

    private val diamondNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.DIAMOND),
            Card(Number(3), Suit.DIAMOND),
            Card(Number(4), Suit.DIAMOND),
            Card(Number(5), Suit.DIAMOND),
            Card(Number(6), Suit.DIAMOND),
            Card(Number(7), Suit.DIAMOND),
            Card(Number(8), Suit.DIAMOND),
            Card(Number(9), Suit.DIAMOND),
            Card(Number(10), Suit.DIAMOND),
        )

    private val cloverNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.CLOVER),
            Card(Number(3), Suit.CLOVER),
            Card(Number(4), Suit.CLOVER),
            Card(Number(5), Suit.CLOVER),
            Card(Number(6), Suit.CLOVER),
            Card(Number(7), Suit.CLOVER),
            Card(Number(8), Suit.CLOVER),
            Card(Number(9), Suit.CLOVER),
            Card(Number(10), Suit.CLOVER),
        )
    private val numberCards: List<Card> = spadeNumberCards + heartNumberCards + diamondNumberCards + cloverNumberCards

    private val spadeFaceCards: List<Card> =
        listOf(Card(Face.JACK, Suit.SPADE), Card(Face.QUEEN, Suit.SPADE), Card(Face.KING, Suit.SPADE))

    private val heartFaceCards: List<Card> =
        listOf(Card(Face.JACK, Suit.HEART), Card(Face.QUEEN, Suit.HEART), Card(Face.KING, Suit.HEART))

    private val diamondFaceCards: List<Card> =
        listOf(
            Card(Face.JACK, Suit.DIAMOND),
            Card(Face.QUEEN, Suit.DIAMOND),
            Card(Face.KING, Suit.DIAMOND),
        )

    private val cloverFaceCards: List<Card> =
        listOf(Card(Face.JACK, Suit.CLOVER), Card(Face.QUEEN, Suit.CLOVER), Card(Face.KING, Suit.CLOVER))

    private val characterCards =
        spadeFaceCards + heartFaceCards + diamondFaceCards + cloverFaceCards

    private val cards: MutableList<Card> = (aceCards + numberCards + characterCards).shuffled().toMutableList()

    fun getCard(): Card = cards.removeFirst()
}
