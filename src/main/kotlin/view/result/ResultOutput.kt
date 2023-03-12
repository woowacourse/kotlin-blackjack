package view.result

import domain.person.Person
import view.ViewCommon.toText

object ResultOutput {
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s - 결과: %s"
    private const val FINAL_OUTCOME_SCRIPT = "## 최종 승패"
    private const val DEALER_SCRIPT = "딜러: "

    fun printPersonCards(person: Person) {
        println(
            INITIAL_CARDS_SCRIPT.format(
                person.name,
                person.showHandOfCards().joinToString(",") { it.toText() },
                person.getTotal(),
            ),
        )
    }

    fun printFinalOutcome() {
        println(FINAL_OUTCOME_SCRIPT)
    }

    fun printDealerProfit(profit: Double) {
        println("${DEALER_SCRIPT}$profit")
    }

    fun printPlayerProfit(playerResult: Map<String, Double>) {
        playerResult.entries.forEach { println("${it.key}: ${it.value}") }
    }
}
