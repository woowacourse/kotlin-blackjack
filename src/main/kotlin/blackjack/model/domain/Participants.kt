package blackjack.model.domain

import blackjack.model.domain.ParticipantStatus.Companion.isBust
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
            if (sum + CardNumber.OTHER_ACE > BUST_STANDARD) return@repeat
            sum += CardNumber.OTHER_ACE
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
