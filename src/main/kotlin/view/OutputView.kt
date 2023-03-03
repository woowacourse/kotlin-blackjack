package view

import domain.card.Card
import domain.gamer.state.PlayerState

object OutputView {
    fun printDivideCard(names: List<String>) {
        println(names.joinToString(", ", "딜러와 ", "에게 2장을 나누었습니다."))
    }

    fun printDealerSettingCard(card: Card) {
        println("딜러: ${printCardForm(card)}")
    }

    fun printParticipantsCards(participants: Map<String, PlayerState>) {
        participants.forEach { it ->
            printParticipantCards(it.key, it.value.cards)
        }
    }

    fun printParticipantCards(name: String, cards: List<Card>) {
        println("${name}카드: ${cards.joinToString(", ") { printCardForm(it) }}")
    }

    private fun printCardForm(card: Card): String {
        return card.cardValue.title + card.shape.shape
    }
}
