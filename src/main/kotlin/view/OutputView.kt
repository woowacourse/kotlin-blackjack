package view

import model.card.CardName
import model.result.PlayerResult

class OutputView {
    fun printDealerAndPlayers(players: List<String>) {
        val playerNames = players.joinToString { it }
        println("\n딜러와 ${playerNames}에게 2장의 나누었습니다.")
    }

    fun printInitialCards(
        dealerCards: List<CardName>,
        playerNames: List<String>,
        playerCards: List<List<CardName>>,
    ) {
        println("딜러: ${printCardNames(dealerCards).first()}")
        playerNames.forEachIndexed { index, playerName ->
            println("$playerName: ${printCardNames(playerCards[index]).joinToString()}")
        }
    }

    fun printPlayerCards(
        playerName: String,
        playerCards: List<CardName>,
    ) {
        println("${playerName}카드: ${printCardNames(playerCards).joinToString()}")
    }

    fun printDealerHit(dealerAddCount: Int) {
        println("\n딜러는 16이하라 ${dealerAddCount}장의 카드를 더 받았습니다.")
    }

    fun printDealerResult(
        dealerCards: List<CardName>,
        dealerScore: Int,
    ) {
        println("\n딜러: ${printCardNames(dealerCards).joinToString()} - 결과: $dealerScore")
    }

    fun printPlayerResult(
        playerNames: List<String>,
        playerCards: List<List<CardName>>,
        playersScore: List<Int>,
    ) {
        playerNames.forEachIndexed { index, playerName ->
            println("$playerName: ${printCardNames(playerCards[index]).joinToString()} - 결과: ${playersScore[index]}")
        }
    }

    fun printResult(dealerResult: Float, playerResults: List<PlayerResult>) {
        println("\n## 최종 수익")
        println("딜러: ${dealerResult.roundToInteger()}")
        playerResults.forEach { playResult ->
            println("${playResult.name}: ${playResult.profit.roundToInteger()}")
        }
    }

    private fun printCardNames(rawCardNames: List<CardName>) =
        rawCardNames.map { (rank, shape) -> rank.mapToTitle() + shape.mapToTitle() }
}
