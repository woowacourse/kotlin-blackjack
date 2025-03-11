package blackjack.model

class Card(
    val rank: CardRank,
    val suit: CardSuit,
) {
    companion object {
        val SINGLE_DECK = CardRank.entries.flatMap { cardRank ->
            CardSuit.entries.map { cardSuit ->
                Card(cardRank, cardSuit)
            }
        }

        operator fun invoke(rank: CardRank, suit: CardSuit): Card {
            return SINGLE_DECK.findLast { it.rank == rank && it.suit == suit }
                ?: throw IllegalArgumentException("[ERROR] 잘못된 카드 번호나 문양입니다.")
        }
    }
}
