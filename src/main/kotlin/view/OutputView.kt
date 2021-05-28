package view

import domain.player.Player
import domain.player.PlayerCards

object OutputView {

    fun printPlayersCards(gamblers: List<Player>) {
        gamblers.forEach {
            printPlayerCard(it)
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println("${player.name} : ${possessCards(player.cards)}")
    }

    private fun possessCards(cards: PlayerCards): String {
        val cardsAsList = cards.cards
        return cardsAsList.joinToString(",") {
            CardSymbol.parse(it)
        }
    }

    fun printResult(gamblers: List<Player>) {
        println("\n## 최종 수익")
        gamblers.forEach {
            printPlayerEarningMoney(it)
        }
        println()
    }

    private fun printPlayerEarningMoney(player: Player) {
        println("${player.name} : ${player.earningMoney.value}")
    }
}
