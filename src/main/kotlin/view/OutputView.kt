package view

import model.Dealer
import model.Player

class OutputView {
    fun printDealerAndPlayers(players: List<String>) {
        val playerNames = players.joinToString(", ") { it }
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun printInitialCards(dealer: Dealer, players: List<Player>) {
        println("딜러: ${dealer.cards.allCards[0]}")
        players.forEach { player ->
            println("${player.name}: ${player.cards.allCards.joinToString(", ") { it.toString() }}")
        }
    }

    fun printHitOrStandPrompt(playerName: String) {
        println("${playerName}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.allCards.joinToString(", ") { it.toString() }}")
    }
}

