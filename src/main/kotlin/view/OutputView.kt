package view

import domain.Participant
import domain.Player

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

fun printResult(players: List<Participant>) {
    for (player in players) {
        println("${getStatusFormat(player)} - 결과: ${player.score()}")
    }
}

private fun getStatusFormat(player: Participant): String {
    return "${player.name}카드: ${player.hand.joinToString(",") { it.name() }}"
}
