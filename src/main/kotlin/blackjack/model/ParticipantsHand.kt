package blackjack.model

data class ParticipantsHand(
    val playerHandCards: List<Hand>,
    val dealerHand: Hand,
) {
    companion object {
        fun from(cards: List<Card>): ParticipantsHand {
            val hands = cards.chunked(DEFAULT_CARDS_COUNT).map { Hand(it.toMutableList()) }
            val playerHands: List<Hand> = hands.drop(1)
            val dealerHands = hands.first()
            return ParticipantsHand(playerHands, dealerHands)
        }

        const val DEFAULT_CARDS_COUNT = 2
        const val DEALER_COUNT = 1
    }
}
