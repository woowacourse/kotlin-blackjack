package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players

class OutputView {
    fun printStartMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printPlayersCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val playersNames: String = players.joinToString(", ") { it.name }
        println("\n${dealer.name}와 ${playersNames}에게 2장의 카드를 나누었습니다.")
        println("${dealer.name}: ${dealer.cards.getCardsInfomation()[0]}")
        players.forEach { player ->
            printPlayerCard(player)
        }
        println()
    }

    fun printDealerBlackjackMessage(
        dealer: Dealer,
        blackjackPlayers: List<Player>,
    ) {
        println("딜러가 블랙잭이므로 게임이 종료됩니다.")
        println("블랙잭: ${dealer.name}, ${blackjackPlayers.joinToString(", ")}")
    }

    fun printPlayerBehaviorGuide(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.getCardsInfomation().joinToString(", ")}")
    }

    fun printBust(player: Player) {
        println("${player.name}의 점수는 ${player.cards.calculateScore()}점으로 21점을 초과하여 죽었습니다.")
    }

    fun printDealerGettingCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printResult(
        dealer: Dealer,
        players: Players,
    ) {
        val results: MutableList<String> = mutableListOf()
        dealer.results.map { result ->
            results.add("${result.value}${result.key.koreanTitle}")
        }
        println(
            "\n${dealer.name}카드: ${
                dealer.cards.getCardsInfomation().joinToString(", ")
            } - 결과: ${dealer.cards.calculateScore()}",
        )
        players.value.forEach { player ->
            println(
                "${player.name}카드: ${
                    player.cards.getCardsInfomation().joinToString(", ")
                } - 결과: ${player.cards.calculateScore()}",
            )
        }

        println("\n## 최종 승패")
        println("${dealer.name}: ${results.joinToString(" ")}")
        players.value.forEach { player ->
            println("${player.name}: ${player.result.koreanTitle}")
        }
    }
}
