package view.result

import domain.person.Dealer
import domain.person.Player
import domain.result.GameResult
import view.result.ResultOutput.printDealerResult
import view.result.ResultOutput.printFinalOutcome
import view.result.ResultOutput.printPersonCards
import view.result.ResultOutput.printPlayerResult

object ResultView {
    fun printPersonsCards(dealer: Dealer, players: List<Player>) {
        printPersonCards(dealer)
        players.forEach { printPersonCards(it) }
        println()
    }

    fun printFinalResult(gameResult: GameResult) {
        printFinalOutcome()
        printDealerResult(gameResult.getDealerResult())
        printPlayerResult(gameResult.getPlayerResult())
    }
}
