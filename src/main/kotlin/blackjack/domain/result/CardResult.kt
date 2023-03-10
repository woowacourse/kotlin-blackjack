package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.participant.Participant

data class CardResult(
    val participant: Participant,
    val cards: List<Card>,
    val scoreSum: Int,
)
