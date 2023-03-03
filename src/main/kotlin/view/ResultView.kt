package view

import domain.person.Dealer
import domain.person.Person
import domain.person.Player
import domain.result.GameResult
import domain.result.OutCome

object ResultView {
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s - 결과: %s"
    private const val FINAL_OUTCOME_SCRIPT = "## 최종 승패"
    private const val DEALER_SCRIPT = "딜러: "
    fun printPersonsCards(dealer: Dealer, players: List<Player>) {
        printPersonCards(dealer)
        players.forEach { printPersonCards(it) }
        println()
    }

    private fun printPersonCards(person: Person) {
        println(
            INITIAL_CARDS_SCRIPT.format(
                person.name,
                person.cards.joinToString(",") { it.toString() },
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
