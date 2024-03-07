package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Players

class OutputView {
    fun printInitCard(dealer: Dealer, players: Players) {
        println("딜러와 ${players.players.joinToString(", ") { it.name }}명의 플레이어에게 2장의 카드를 나누었습니다.")
        println("딜러의 카드: ${dealer.getFirstCard()}")
        players.players.forEach { player ->
            println("${player.name}카드: ${player.getFirstCard()}")
        }
    }
}
