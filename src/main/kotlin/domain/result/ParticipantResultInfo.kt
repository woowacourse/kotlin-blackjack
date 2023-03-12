package domain.result

import domain.Name
import domain.card.Card

data class ParticipantResultInfo(
    val name: Name,
    val profitMoney: Int,
    val cards: List<Card>
)
