package view

import domain.Player

fun printStatus(players: List<Player>) {
    println("${players.joinToString(",") { it.name }} 에게 2장의 나누었습니다.")
    for (player in players) {
        println(getStatusFormat(player))
    }
}

fun printResult(players: List<Player>) {
    for (player in players) {
        println("${getStatusFormat(player)} - 결과: ${player.score()}")
    }
}

private fun getStatusFormat(player: Player): String {
    return "${player.name}카드: ${player.hand.joinToString(",") { it.name() }}"
}
