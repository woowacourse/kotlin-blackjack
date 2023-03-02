package domain.view

import domain.person.Dealer
import domain.person.Person
import domain.person.Player
import domain.result.GameResult
import domain.result.OutCome

class ResultView {
    fun printPersonsCards(dealer: Dealer, players: List<Player>) {
        printPersonCards(dealer)
        players.forEach { printPersonCards(it) }
    }

    private fun printPersonCards(person: Person) {
        println(
            "${person.name}카드: " +
                person.cards.joinToString(",") { it.toString() } +
                " - 결과: ${person.getTotalCardNumber()}"
        )
    }

    fun printFinalResult(gameResult: GameResult) {
        println("## 최종 승패")
        printDealerResult(gameResult.getDealerResult())
        printPlayerResult(gameResult.getPlayerResult())
    }

    private fun printDealerResult(dealerResult: Map<OutCome, Int>) {
        print("딜러:")
        dealerResult.entries.forEach { print(" ${it.value}${it.key.text}") }
        println()
    }

    private fun printPlayerResult(playerResult: Map<String, OutCome>) {
        playerResult.entries.forEach { println("${it.key}: ${it.value.text}") }
    }
}
