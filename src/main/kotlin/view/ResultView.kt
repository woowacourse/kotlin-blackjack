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
                person.showHandOfCards().joinToString(",") { it.toString() },
                person.getTotalCardNumber(GetAppropriateSum()),
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
        dealerResult.entries.forEach { print(" ${it.value}${getOutComeText(it.key)}") }
        println()
    }

    private fun printPlayerResult(playerResult: Map<String, OutCome>) {
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
