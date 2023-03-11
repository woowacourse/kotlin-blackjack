package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.money.Money

data class GameResult(
    val participantName: String,
    val cards: List<Card>,
    val scoreSum: Int,
    val profit: Money,
)
