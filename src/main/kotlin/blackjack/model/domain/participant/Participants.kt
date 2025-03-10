package blackjack.model.domain.participant

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardNumber
import blackjack.model.domain.participant.ParticipantStatus.Companion.isBust
import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

abstract class Participants() {
    abstract val name: String
    abstract val cards: MutableList<Card>
    abstract var status: ParticipantStatus

    val sumCardNumber: Int get() = getSumNumber()
    val cardDeck get() = cards.toList()

    private fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }
        val aCount: Int = cards.count { it.cardNumber == CardNumber.Ace }

        repeat(aCount) {
            if (sum + CardNumber.BONUS_SCORE > BUST_STANDARD) return@repeat
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun checkBust() {
        status = isBust(sumCardNumber)
    }
}
