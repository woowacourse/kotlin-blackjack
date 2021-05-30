package blackjackgame.view

import blackjackgame.model.result.Result
import blackjackgame.model.card.Card

fun printStatus(players: List<Pair<String, List<Card>>>) {
    println()
    println("${players.joinToString(",") { it.first }}에게 2장의 카드를 나누었습니다.")
    players.forEach {
        println("${it.first}카드: ${printCard(it.second)} ")
    }
    println()
}

fun printStatus(player: Pair<String, List<Card>>) {
    println("${player.first}카드: ${printCard(player.second)} ")
}

private fun printCard(cards: List<Card>): String {
    val result = cards.map { "${it.denomination.value}${it.suit.value}" }
    return result.joinToString(",")
}

fun printFinalResult(results: List<Triple<String, List<Card>, Int>>) {
    println()
    results.forEach {
        println("${it.first}카드: ${printCard(it.second)} - 결과: ${it.third}")
    }
    println()
}

fun printDealerTurnResult() {
    println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
}

fun printWinLoseResult(results: List<Pair<String, Result>>) {
    results.forEach {
        println("${it.first}: ${it.second}")
    }
}