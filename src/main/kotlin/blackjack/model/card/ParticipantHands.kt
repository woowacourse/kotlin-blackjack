package blackjack.model.card

data class ParticipantHands(
    val playerHandCards: List<Hand>,
    val dealerHand: Hand,
) {
    companion object {
        private const val INIT_HANDS_COUNT = 2

        fun from(cards: List<Card>): ParticipantHands {
            val hands = cards.chunked(INIT_HANDS_COUNT).map { Hand(it) }
            val playerHands: List<Hand> = hands.drop(1)
            val dealerHands = hands.first()
            return ParticipantHands(playerHands, dealerHands)
        }
    }
}
