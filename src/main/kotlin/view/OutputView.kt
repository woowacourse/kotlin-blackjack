package view

import model.PlayerResult
import view.ViewMapper.mapToTitle

class OutputView {
    fun printDealerAndPlayers(players: List<String>) {
        val playerNames = players.joinToString { it }
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun printInitialCards(
        dealerCards: List<Pair<String, String>>,
        playerNames: List<String>,
        playerCards: List<List<Pair<String, String>>>,
    ) {
        println("딜러: ${printCardNames(dealerCards).first()}")
        playerNames.forEachIndexed { index, playerName ->
            println("$playerName: ${printCardNames(playerCards[index]).joinToString()}")
        }
    }

    fun printPlayerCards(
        playerName: String,
        playerCards: List<Pair<String, String>>,
    ) {
        println("${playerName}카드: ${printCardNames(playerCards).joinToString()}")
    }

    fun printDealerHit(dealerAddCount: Int) {
        println("\n딜러는 16이하라 ${dealerAddCount}장의 카드를 더 받았습니다.")
    }

    fun printDealerResult(
        dealerCards: List<Pair<String, String>>,
        dealerScore: Int,
    ) {
        println("\n딜러: ${printCardNames(dealerCards).joinToString()} - 결과: $dealerScore")
    }

    fun printPlayerResult(
        playerNames: List<String>,
        playerCards: List<List<Pair<String, String>>>,
        playersScore: List<Int>,
    ) {
        playerNames.forEachIndexed { index, playerName ->
            println("$playerName: ${printCardNames(playerCards[index]).joinToString()} - 결과: ${playersScore[index]}")
        }
    }

    fun printResult(
        dealerWins: Int,
        dealerLosses: Int,
        playerResults: List<PlayerResult>,
    ) {
        println("\n## 최종 승패")
        println("딜러: ${dealerWins}승 ${dealerLosses}패")

        playerResults.forEach { playResult ->
            println("${playResult.name}: ${playResult.result}")
        }
    }

    private fun printCardNames(rawCardNames: List<Pair<String, String>>) =
        rawCardNames.map { (rank, shape) -> rank.mapToTitle() + shape.mapToTitle() }

}
