package blackjack.view

import blackjack.domain.gamer.Dealer
import blackjack.domain.result.GameResultBoard
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Player

fun printStatus(players: List<Player>, dealer: Dealer) {
    println("딜러와 ${players.joinToString(",") { it.name }} 에게 2장의 나누었습니다.")
    println("${dealer.name}: ${dealer.hand[0].name()}")
    for (player in players) {
        println(statusFormat(player))
    }
}

fun printStatus(players: List<Player>) {
    for (player in players) {
        println(statusFormat(player))
    }
}

fun printResult(gamers: List<Gamer>) {
    for (player in gamers) {
        println("${statusFormat(player)} - 결과: ${player.score()}")
    }
}

private fun statusFormat(gamers: Gamer): String {
    return "${gamers.name}카드: ${gamers.hand.joinToString(",") { it.name() }}"
}

fun printDealerHit(){
    println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
}

fun printGameResultBoard(resultBoard: GameResultBoard) {
    println("## 최종 승패")
    val dealerResult = resultBoard.dealerResult()
    println("딜러: ${dealerResult.map { (k, v) -> v.toString() + k.result }.joinToString(" ")}")
    for (result in resultBoard) {
        println("${result.key.name}: ${result.value.result}")
    }
}
