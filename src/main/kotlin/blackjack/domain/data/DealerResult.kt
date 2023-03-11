package blackjack.domain.data

import blackjack.domain.card.Card

data class DealerResult(
    override val name: String,
    override val cards: List<Card>,
    override val score: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    override val profit: Int
) : ParticipantResult
