package view.result

import domain.person.Person
import domain.person.Persons
import domain.result.Casino
import view.ViewCommon.toText

object ResultView {
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s - 결과: %s"
    private const val FINAL_OUTCOME_SCRIPT = "## 최종 수익"
    private const val DEALER_SCRIPT = "딜러: "

    fun printResult(casino: Casino) {
        printPersonsCards(casino.persons)
        printFinalResult(casino)
    }

    private fun printPersonsCards(persons: Persons) {
        printPersonCards(persons.dealer)
        persons.players.forEach { printPersonCards(it) }
        println()
    }

    private fun printPersonCards(person: Person) {
        println(
            INITIAL_CARDS_SCRIPT.format(
                person.name,
                person.showHandOfCards().joinToString(",") { it.toText() },
                person.getTotal(),
            ),
        )
    }

    private fun printFinalResult(casino: Casino) {
        println(FINAL_OUTCOME_SCRIPT)
        println("${DEALER_SCRIPT}${casino.getDealerProfit()}")
        casino.getPlayersProfit().entries
            .forEach { println("${it.key}: ${it.value}") }
    }
}
