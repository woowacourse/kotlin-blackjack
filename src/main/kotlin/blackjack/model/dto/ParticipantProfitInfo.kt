package blackjack.model.dto

import blackjack.model.Money

data class ParticipantProfitInfo(
    val name: String,
    val profit: Money,
)
