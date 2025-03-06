package blackjack.uiModel

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.person.Person
import blackjack.domain.person.Player

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

        private fun Card.toUiString(): String =
            when (number) {
                CardNumber.ACE -> "A"
                CardNumber.KING -> "K"
                CardNumber.QUEEN -> "Q"
                CardNumber.JACK -> "J"
                else -> number.value.toString()
            } + pattern.value
    }
}
