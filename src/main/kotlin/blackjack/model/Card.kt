package blackjack.model

class Card private constructor(
    val rank: CardRank,
    val suit: CardSuit,
) {
    companion object {
        val SINGLE_DECK = CardRank.entries.flatMap { cardRank ->
            CardSuit.entries.map { cardSuit ->
                Card(cardRank, cardSuit)
            }
        }

        fun getCard(rank: CardRank, suit: CardSuit): Card {
            return SINGLE_DECK.filter{it.rank==rank&&it.suit==suit}[0]
        }
    }
}
