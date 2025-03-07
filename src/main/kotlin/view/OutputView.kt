package view

import model.PlayerResult

class OutputView {
    fun printDealerAndPlayers(players: List<String>) {
        val playerNames = players.joinToString(", ") { it }
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun printInitialCards(
        dealerCards: List<String>,
        playerNames: List<String>,
        playerCards: List<List<String>>,
    ) {
        println("딜러: ${dealerCards.first()}")
        playerNames.forEachIndexed { index, playerName ->
            println("$playerName: ${playerCards[index].joinToString(", ")}")
        }
    }

    fun printPlayerCards(
        playerName: String,
        playerCards: List<String>,
    ) {
        println("${playerName}카드: ${playerCards.joinToString(", ")}")
    }

    fun printDealerHit(dealerAddCount: Int) {
        println("\n딜러는 16이하라 ${dealerAddCount}장의 카드를 더 받았습니다.")
    }

    fun printDealerResult(
        dealerCards: List<String>,
        dealerScore: Int,
    ) {
        println("\n딜러: ${dealerCards.joinToString(", ")} - 결과: $dealerScore")
    }

    fun printPlayerResult(
        playerNames: List<String>,
        playerCards: List<List<String>>,
        playersScore: List<Int>,
    ) {
        playerNames.forEachIndexed { index, playerName ->
            println("$playerName: ${playerCards[index].joinToString(", ")} - 결과: ${playersScore[index]}")
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
}
