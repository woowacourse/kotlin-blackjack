package blackjack.view

import blackjack.model.CompetitionResult
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players

class OutputView {
    fun printInitCard(dealer: Dealer, players: Players) {
        println("딜러와 ${players.gamePlayers.joinToString(", ") { it.name }}명의 플레이어에게 2장의 카드를 나누었습니다.")
        println("딜러의 카드: ${dealer.getFirstCard()}")
        players.gamePlayers.forEach { player ->
            println("${player.name}카드: ${player.getCards()}")
        }
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${player.getCards()}")
    }

    fun printDealerAddCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printCardResult(dealer: Dealer, players: Players) {
        println("딜러 카드: ${dealer.getAllCard()}")
        players.gamePlayers.forEach { player ->
            println("${player.name}카드: ${player.getCards()}")
        }
    }

    fun printGameResult(result: Map<String, CompetitionResult>) {
        println("## 최종 승패")

        val counts = result.values.groupingBy { it }.eachCount()
        val wins = counts.getOrDefault(CompetitionResult.WIN, 0)
        val losses = counts.getOrDefault(CompetitionResult.LOSE, 0)
        val draws = counts.getOrDefault(CompetitionResult.SAME, 0)

        println("딜러: ${losses}승 ${wins}패 ${draws}무")
        result.forEach { (playerName, competitionResult) ->
            println("$playerName: ${competitionResult.result}")
        }
    }
}
