package blackjack.view

import blackjack.domain.ResultState
import blackjack.domain.card.Card
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.score.ScoreCalculator

class OutputView {
    fun printNameMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printDrawMessage(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러와 ${players.joinToString(",") { it.name }}에게 2장을 나누었습니다.")
        println("딜러: ${printCards(dealer.hand.cards)}")
        players.forEach { player ->
            println(printPlayerCards(player))
        }
    }

    fun printFlagMessage(name: String) {
        println("${name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
    }

    fun printPlayerCards(player: Player): String {
        return "${player.name}카드: ${printCards(player.hand.cards)}"
    }

    fun printDrawStatus(player: Player) {
        println(printPlayerCards(player))
    }

    fun printDealerDrawMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러: ${printCards(dealer.hand.cards)} - 결과: ${ScoreCalculator.calculate(dealer.hand)}")
        players.forEach { player ->
            println(printPlayerCards(player) + " - 결과: ${ScoreCalculator.calculate(player.hand)}")
        }
    }

    fun printResult(result: Map<Player, ResultState>) {
        println("## 최종 승패")
        println(
            "딜러: ${result.keys.count { result[it] == ResultState.LOSE }}승 " +
                "${result.keys.count { result[it] == ResultState.DRAW }}무 " +
                "${result.keys.count { result[it] == ResultState.WIN }}패",
        )
    }

    private fun printCards(cards: List<Card>): String {
        return cards.joinToString(",") { "${it.number.value}${it.pattern.value}" }
    }
}
