package domain.result

import domain.Name
import domain.ProfitMoney
import domain.card.Card

data class ParticipantResultInfo(
    val name: Name,
    val profitMoney: ProfitMoney,
    val cards: List<Card>
)
