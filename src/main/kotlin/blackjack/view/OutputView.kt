package blackjack.view

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Player
import blackjack.domain.result.GameResultBoard

fun printStatus(gamers: Pair<Dealer, List<Player>>) {
    println("딜러와 ${gamers.second.joinToString(",") { it.name }} 에게 2장의 나누었습니다.")
    println("${gamers.first.name}: ${gamers.first.cards()[0].name()}")
    for (player in gamers.second) {
        println(statusFormat(player))
    }
}

fun printPlayerStatus(player: Player) {
    println(statusFormat(player))
}

fun printDealerHit() {
    println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
}

fun printResult(gamers: List<Gamer>) {
    for (player in gamers) {
        println("${statusFormat(player)} - 결과: ${player.score().value}")
    }
}

private fun statusFormat(gamers: Gamer): String {
    return "${gamers.name}카드: ${gamers.cards().joinToString(",") { it.name() }}"
}

fun printGameResultBoard(resultBoard: GameResultBoard) {
    println("## 최종 승패")
    val dealerResult = resultBoard.dealerResult()
    println("딜러: ${dealerResult.map { (k, v) -> v.toString() + k.result }.joinToString(" ")}")
    for (result in resultBoard) {
        println("${result.key.name}: ${result.value.result}")
    }
}
