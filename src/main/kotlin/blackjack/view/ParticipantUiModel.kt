package blackjack.view

import blackjack.model.card.Card
import blackjack.model.participant.Participant

data class ParticipantUiModel(
    val name: String,
    val cards: List<Card>,
    val score: Int,
)

fun Participant.toUiModel(): ParticipantUiModel {
    return ParticipantUiModel(
        name = name,
        cards = hand.cards,
        score = sumScore(),
    )
}
