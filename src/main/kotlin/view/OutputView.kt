package view

import domain.player.PlayerCards
import dto.PlayerResponse

object OutputView {

    fun printPlayersCards(players: List<PlayerResponse>) {
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printPlayerCard(player: PlayerResponse) {
        println("${player.name} : ${possessCards(player.cards)}")
    }

    private fun possessCards(cards: PlayerCards): String {
        val cardsAsList = cards.cards
        return cardsAsList.joinToString(",") {
            CardPrintSymbol.parse(it)
        }
    }

    fun printResult(players: List<PlayerResponse>) {
        println("## 최종 수익")
        players.forEach {
            printPlayerEarningMoney(it)
        }
    }

    private fun printPlayerEarningMoney(player: PlayerResponse) {
        println("${player.name} : ${player.earningMoney.value}")
    }
}
