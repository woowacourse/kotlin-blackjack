package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player

class OutputView {
    fun printStartMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printPlayersCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val playersNames: String = players.joinToString(", ") { it.name }
        println("${dealer.name}와 ${playersNames}에게 2장의 나누었습니다.")
        println("${dealer.name}: ${dealer.cards.getCardsInfomation().joinToString(", ")}")
        players.forEach { player ->
            println("${player.name}: ${player.cards.getCardsInfomation().joinToString(", ")}")
        }
    }
}
