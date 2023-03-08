package view.result

import domain.card.strategy.SumStrategy.getAppropriateSum
import domain.person.Person
import domain.result.OutCome
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
                person.getTotalCardNumber { getAppropriateSum() },
            ),
        )
    }

    fun printFinalOutcome() {
        println(FINAL_OUTCOME_SCRIPT)
    }

    fun printDealerResult(dealerResult: Map<OutCome, Int>) {
        print(DEALER_SCRIPT)
        dealerResult.entries.forEach { print(" ${it.value}${getOutComeText(it.key)}") }
        println()
    }

    fun printPlayerResult(playerResult: Map<String, OutCome>) {
        playerResult.entries.forEach { println("${it.key}: ${getOutComeText(it.value)}") }
    }

    private fun getOutComeText(outCome: OutCome): String {
        return when (outCome) {
            OutCome.WIN -> "승"
            OutCome.LOSE -> "패"
            OutCome.DRAW -> "무"
        }
    }
}
