package view

import domain.Dealer
import domain.GameResultBoard
import domain.Gamer
import domain.Player

fun printStatus(players: List<Player>, dealer: Dealer) {
    println("딜러와 ${players.joinToString(",") { it.name }} 에게 2장의 나누었습니다.")
    println("${dealer.name}: ${dealer.hand[0].name()}")
    for (player in players) {
        println(getStatusFormat(player))
    }
}

fun printStatus(players: List<Player>) {
    for (player in players) {
        println(getStatusFormat(player))
    }
}

fun printResult(gamers: List<Gamer>) {
    for (player in gamers) {
        println("${getStatusFormat(player)} - 결과: ${player.score()}")
    }
}

private fun getStatusFormat(gamers: Gamer): String {
    return "${gamers.name}카드: ${gamers.hand.joinToString(",") { it.name() }}"
}

fun printGameResultBoard(resultBoard: GameResultBoard) {
    val dealerResult = resultBoard.dealerResult()
    println("딜러: ${dealerResult.map { (k, v) -> v.toString() + k.result }.joinToString(" ")}")
    for (result in resultBoard) {
        println("${result.key.name}: ${result.value.result}")
    }
}

fun printDealerHit(){
    println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
}