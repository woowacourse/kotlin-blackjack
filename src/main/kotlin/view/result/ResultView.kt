package view.result

import domain.person.Persons
import domain.result.GameResult
import view.result.ResultOutput.printDealerResult
import view.result.ResultOutput.printFinalOutcome
import view.result.ResultOutput.printPersonCards
import view.result.ResultOutput.printPlayerResult

object ResultView {

    fun printResult(persons: Persons, gameResult: GameResult) {
        printPersonsCards(persons)
        printFinalResult(gameResult)
    }

    private fun printPersonsCards(persons: Persons) {
        printPersonCards(persons.dealer)
        persons.players.forEach { printPersonCards(it) }
        println()
    }

    private fun printFinalResult(gameResult: GameResult) {
        printFinalOutcome()
        printDealerResult(gameResult.getDealerResult())
        printPlayerResult(gameResult.getPlayerResult())
    }
}
