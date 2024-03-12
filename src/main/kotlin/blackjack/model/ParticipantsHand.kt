package blackjack.model

import blackjack.model.ParticipantsHand.Companion.removeSeveral

data class ParticipantsHand(
    val playerHandCards: List<Hand>,
    val dealerHand: Hand,
) {
    companion object {
        fun from(cards: MutableList<Card>): ParticipantsHand {
            val hands = cards.chunked(DEFAULT_CARDS_COUNT).map { Hand(it) }
            val playerHands: List<Hand> = hands.drop(DEALER_COUNT)
            val dealerHands = hands.first()
            val spreadCardsCount = hands.count() * 2
            cards.removeSeveral(spreadCardsCount)
            return ParticipantsHand(playerHands, dealerHands)
        }

        private fun MutableList<Card>.removeSeveral(num: Int) {
            repeat(num) {
                this.removeFirst()
            }
        }

        const val DEFAULT_CARDS_COUNT = 2
        const val DEALER_COUNT = 1
    }
}
