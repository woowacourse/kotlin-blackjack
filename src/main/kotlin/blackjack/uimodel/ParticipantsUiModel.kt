package blackjack.uimodel

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player

data class ParticipantsUiModel(
    val name: String,
    val cards: List<String>,
    val score: Int,
) {
    companion object {
        fun create(
            participant: Participant,
            isFirstTurn: Boolean = false,
        ): ParticipantsUiModel {
            return ParticipantsUiModel(
                name = (participant as? Player)?.name ?: "딜러",
                cards = participant.visibleCard(isFirstTurn).map { it.toUiString() },
                score = participant.score,
            )
        }

        private fun Card.toUiString(): String = "${number.toUiString()}${pattern.toUiString()}"

        private fun CardNumber.toUiString(): String =
            when (this) {
                CardNumber.ACE -> ACE
                CardNumber.JACK -> JACK
                CardNumber.QUEEN -> QUEEN
                CardNumber.KING -> KING
                else -> this.value.toString()
            }

        private fun CardPattern.toUiString(): String =
            when (this) {
                CardPattern.HEART -> HEART
                CardPattern.SPADE -> SPADE
                CardPattern.DIAMOND -> DIAMOND
                CardPattern.CLOVER -> CLOVER
            }

        private const val ACE = "A"
        private const val JACK = "J"
        private const val QUEEN = "Q"
        private const val KING = "K"

        private const val HEART = "하트"
        private const val SPADE = "스페이드"
        private const val DIAMOND = "다이아몬드"
        private const val CLOVER = "클로버"
    }
}
