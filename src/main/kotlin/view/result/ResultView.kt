package view.result

import domain.person.Persons
import domain.result.Casino
import view.result.ResultOutput.printDealerProfit
import view.result.ResultOutput.printFinalOutcome
import view.result.ResultOutput.printPersonCards
import view.result.ResultOutput.printPlayerProfit

object ResultView {

    fun printResult(persons: Persons, casino: Casino) {
        printPersonsCards(persons)
        printFinalResult(casino)
    }

    private fun printPersonsCards(persons: Persons) {
        printPersonCards(persons.dealer)
        persons.players.forEach { printPersonCards(it) }
        println()
    }

    private fun printFinalResult(casino: Casino) {
        printFinalOutcome()
        printDealerProfit(casino.getDealerProfit())
        printPlayerProfit(casino.getPlayersProfit())
    }
}
