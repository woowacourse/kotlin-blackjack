package blackjack.uimodel

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player

data class ParticipantsUiModel(
    val name: String?,
    val cards: List<String>,
    val score: Int,
) {
    companion object {
        fun create(participant: Participant): ParticipantsUiModel {
            return ParticipantsUiModel(
                name = (participant as? Player)?.name ?: "딜러",
                cards = participant.hand.map { it.toUiString() },
                score = participant.score(),
            )
        }

        private fun Card.toUiString(): String = "${number.toUiString()}${pattern.toUiString()}"

        private fun CardNumber.toUiString(): String =
            when (this) {
                CardNumber.ACE -> GameRule.ACE
                CardNumber.JACK -> GameRule.JACK
                CardNumber.QUEEN -> GameRule.QUEEN
                CardNumber.KING -> GameRule.KING
                else -> this.value.toString()
            }

        private fun CardPattern.toUiString(): String =
            when (this) {
                CardPattern.HEART -> GameRule.HEART
                CardPattern.SPADE -> GameRule.SPADE
                CardPattern.DIAMOND -> GameRule.DIAMOND
                CardPattern.CLOVER -> GameRule.CLOVER
            }
    }
}
