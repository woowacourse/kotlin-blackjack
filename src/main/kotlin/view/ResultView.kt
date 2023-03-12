package view

import domain.card.Card
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.money.Profit
import domain.person.Participants
import domain.person.Person
import domain.person.Player

class ResultView {
    private fun cardToString(card: Card) =
        ViewUtils.cardNumberToText(card.number) + ViewUtils.cardShapeToText(card.shape)

    fun printInitialSetting(participants: Participants) {
        val players = participants.players
        val dealer = participants.dealer
        println()
        println(SHARE_TWO_CARDS_SCRIPT.format(dealer.name, players.joinToString(", ") { it.name }))
        println(INITIAL_CARDS_SCRIPT.format(dealer.name, dealer.showOneCard().joinToString { cardToString(it) }))
        players.forEach { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(person: Person) {
        println(INITIAL_CARDS_SCRIPT.format(person.name, person.getHandCards().joinToString(", ") { cardToString(it) }))
    }

    fun printDealerGetCardOrNot(isGetCard: Boolean) {
        if (isGetCard) {
            println(DEALER_ONE_MORE_CARD_SCRIPT + "\n")
            return
        }
        println(DEALER_NO_MORE_CARD_SCRIPT)
    }

    fun printPersonsCardsResult(participants: Participants) {
        printPersonCardsResult(participants.dealer)
        participants.players.forEach { printPersonCardsResult(it) }
        println()
    }

    private fun printPersonCardsResult(person: Person) {
        println(
            RESULT_CARDS_SCRIPT.format(
                person.name,
                person.getHandCards().joinToString(", ") { cardToString(it) },
                person.score.value,
            ),
        )
    }

    fun printFinalResult(dealerResult: Profit, playerResult: Map<Player, Profit>) {
        println(FINAL_OUTCOME_SCRIPT)
        println(DEALER_SCRIPT + dealerResult.value)
        playerResult.entries.forEach { println("${it.key.name}: ${it.value.value}") }
    }

    companion object {
        private const val FINAL_OUTCOME_SCRIPT = "## 최종 수익"
        private const val DEALER_SCRIPT = "딜러: "
        private const val SHARE_TWO_CARDS_SCRIPT = "%s와 %s에게 2장의 카드를 나누었습니다."
        private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s"
        private const val RESULT_CARDS_SCRIPT = "%s 카드: %s - 결과: %s"
        private const val DEALER_ONE_MORE_CARD_SCRIPT = "딜러는 ${DEALER_STAND_CONDITION}이하라 한장의 카드를 더 받았습니다."
        private const val DEALER_NO_MORE_CARD_SCRIPT = "딜러는 ${DEALER_STAND_CONDITION}초과라 한장의 카드를 받지 않습니다."
    }
}
