package blackjack.domain.data

import blackjack.domain.card.Card

interface ParticipantResult {
    val name: String
    val cards: List<Card>
    val score: Int
    val profit: Int
}
