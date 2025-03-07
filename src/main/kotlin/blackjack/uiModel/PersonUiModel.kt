package blackjack.uiModel

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import blackjack.uiModel.PersonUiModel.Companion.toUiString

data class PersonUiModel(
    val name: String?,
    val cards: List<String>,
    val score: Int,
) {
    companion object {
        fun create(person: Person): PersonUiModel {
            return PersonUiModel(
                name = (person as? Player)?.name ?: "딜러",
                cards = person.cards().map { it.toUiString() },
                score = person.score(),
            )
        }

        private fun Card.toUiString(): String = "${number.toUiString()}${pattern.toUiString()}"

        private fun CardNumber.toUiString(): String =
            when (this) {
                CardNumber.ACE -> "A"
                CardNumber.KING -> "K"
                CardNumber.QUEEN -> "Q"
                CardNumber.JACK -> "J"
                else -> this.value.toString()
            }

        private fun CardPattern.toUiString(): String =
            when (this) {
                CardPattern.HEART -> "하트"
                CardPattern.SPADE -> "스페이드"
                CardPattern.DIAMOND -> "다이아몬드"
                CardPattern.CLOVER -> "클로버"
            }
    }
}
