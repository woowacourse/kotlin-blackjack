package blackjack.domain.data

import blackjack.domain.card.Card
import blackjack.domain.result.GameResult

data class PlayerResult(
    override val name: String,
    override val cards: List<Card>,
    override val score: Int,
    val gameResult: GameResult,
    override val profit: Int
) : ParticipantResult
