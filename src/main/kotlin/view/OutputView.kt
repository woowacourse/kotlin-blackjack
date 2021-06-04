package view

import domain.participant.Participant
import domain.ResultType

fun printInitMessage(players: List<Participant>) {
    println("${players.joinToString(",") { it.name }} 에게 2장을 나누었습니다.")
    printStatus(players)
}

fun printStatus(players: List<Participant>) {
    for (player in players) {
        println(getStatusFormat(player))
    }
    println()
}

fun printStatusWithScore(players: List<Participant>) {
    for (player in players) {
        println("${getStatusFormat(player)} - 결과: ${player.score()}")
    }
}

fun printResult(gameResult: List<Pair<String, Int>>) {
    println("## 최종 승패")
    gameResult.forEach { println("${it.first}: ${it.second}") }
}

private fun getStatusFormat(player: Participant): String {
    return "${player.name}카드: ${player.hand.joinToString(",") { it.name() }}"
}
