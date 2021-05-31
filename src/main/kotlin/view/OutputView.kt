package view

import controller.PlayerResponse
import domain.player.PlayerCards

object OutputView {

    fun printPlayersCards(players: List<PlayerResponse>) {
        players.forEach {
            printPlayerCard(it)
        }
        println()
    }

    fun printPlayerCard(player: PlayerResponse) {
        println("${player.name} : ${possessCards(player.cards)}")
    }

    private fun possessCards(cards: PlayerCards): String {
        val cardsAsList = cards.cards
        return cardsAsList.joinToString(",") {
            CardSymbol.parse(it)
        }
    }

    fun printResult(players: List<PlayerResponse>) {
        println("\n## 최종 수익")
        players.forEach {
            printPlayerEarningMoney(it)
        }
        println()
    }

    private fun printPlayerEarningMoney(player: PlayerResponse) {
        println("${player.name} : ${player.earningMoney.value}")
    }
}
