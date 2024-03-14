package blackjack.model.domain

import blackjack.model.entitiy.Card
import blackjack.model.entitiy.CardRank

data class ParticipantInfo(
    val name: String = "",
    var cards: Set<Card> = setOf(),
) {
    fun sumCardValues(): Int {
        var total = cards.sumOf { card -> card.cardRank.value }

        if (total <= 11 && hasAce()) total += 10

        return total
    }

    private fun hasAce() = cards.any { it.cardRank == CardRank.ACE }
}
