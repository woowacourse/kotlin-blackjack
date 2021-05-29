package blackjackgame.view

import blackjackgame.model.card.Card

fun printStatus(players: List<Pair<String, List<Card>>>) {
    println("${players.joinToString(",") { it.first }}에게 2장의 카드를 나누었습니다.")
    players.forEach {
        println("${it.first}카드: ${printCard(it.second)} ")
    }
}

fun printCard(cards: List<Card>): String {
    val result = cards.map { "${it.denomination.value}${it.suit.value}" }
    return result.joinToString(",")
}