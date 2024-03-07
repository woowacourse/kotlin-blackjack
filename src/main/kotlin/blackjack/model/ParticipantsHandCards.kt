package blackjack.model

data class ParticipantsHandCards(
    val playerHandCards: List<HandCards>,
    val dealerHandCards: HandCards,
) {
    companion object {
        fun from(cards: List<Card>): ParticipantsHandCards {
            val hands = cards.chunked(2).map { HandCards(it) }
            val playerHands: List<HandCards> = hands.drop(1)
            val dealerHands = hands.first()
            return ParticipantsHandCards(playerHands, dealerHands)
        }
    }
}
