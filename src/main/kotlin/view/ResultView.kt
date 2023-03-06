package view

import domain.card.Card
import domain.constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.person.Dealer
import domain.person.Person
import domain.person.Player
import domain.result.GameResult
import domain.result.OutCome

object ResultView {
    private const val FINAL_OUTCOME_SCRIPT = "## 최종 승패"
    private const val DEALER_SCRIPT = "딜러: "
    private const val SHARE_TWO_CARDS_SCRIPT = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s"
    private const val RESULT_CARDS_SCRIPT = "%s 카드: %s - 결과: %s"
    private const val DEALER_ONE_MORE_CARD_SCRIPT = "딜러는 ${DEALER_STAND_CONDITION}이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_NO_MORE_CARD_SCRIPT = "딜러는 ${DEALER_STAND_CONDITION}초과라 한장의 카드를 받지 않습니다."

    private fun cardToString(card: Card) =
        ViewUtils.cardNumberToText(card.number) + ViewUtils.cardShapeToText(card.shape)

    fun printInitialSetting(players: List<Player>, dealer: Dealer) {
        println()
        println(SHARE_TWO_CARDS_SCRIPT.format(dealer.name, players.joinToString(", ") { it.name }))
        println(INITIAL_CARDS_SCRIPT.format(dealer.name, dealer.showOneCard().joinToString { cardToString(it) }))
        players.forEach { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(person: Person) {
        println(INITIAL_CARDS_SCRIPT.format(person.name, person.cards.joinToString(", ") { cardToString(it) }))
    }

    fun printDealerGetMoreCard() = println(DEALER_ONE_MORE_CARD_SCRIPT + "\n")

    fun printDealerNoMoreCard() = println(DEALER_NO_MORE_CARD_SCRIPT + "\n")

    fun printPersonsCardsResult(dealer: Dealer, players: List<Player>) {
        printPersonCardsResult(dealer)
        players.forEach { printPersonCardsResult(it) }
        println()
    }

    private fun printPersonCardsResult(person: Person) {
        println(
            RESULT_CARDS_SCRIPT.format(
                person.name,
                person.cards.joinToString(",") { cardToString(it) },
                person.getTotalCardNumber(),
            ),
        )
    }

    fun printFinalResult(gameResult: GameResult) {
        println(FINAL_OUTCOME_SCRIPT)
        printDealerResult(gameResult.getDealerResult())
        printPlayerResult(gameResult.getPlayerResult())
    }

    private fun printDealerResult(dealerResult: Map<OutCome, Int>) {
        print(DEALER_SCRIPT)
        dealerResult.entries.forEach { print(" ${it.value}${it.key.text}") }
        println()
    }

    private fun printPlayerResult(playerResult: Map<String, OutCome>) {
        playerResult.entries.forEach { println("${it.key}: ${it.value.text}") }
    }
}
