package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.PlayerResults
import blackjack.domain.Suit

object OutputView {
    private const val SEPARATOR = ", "

    fun printFirstOpenCards(cards: Map<String, List<Card>>) {
        println("${cards.keys.first()}와 ${cards.keys.drop(1).joinToString(SEPARATOR)}에게 2장의 카드를 나누었습니다.")
        printCards(cards)
    }

    fun printCards(cards: Map<String, List<Card>>) {
        cards.forEach { (name, cards) ->
            println("$name 카드: ${cards.joinToString(SEPARATOR) { it.toText() }}")
        }
    }

    fun printDrawn(participant: Participant) {
        when (participant) {
            is Dealer -> println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            is Player -> println(
                "${participant.name} 카드: ${participant.getCards().joinToString(SEPARATOR) { it.toText() }}"
            )
        }
    }

    fun printDealerDraw() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printScores(cards: Map<String, List<Card>>, scores: Map<String, Int>) {
        scores.forEach { (name, score) ->
            printScore(name, cards[name] ?: emptyList(), score)
        }
        printInterval()
    }

    private fun printScore(name: String, cards: List<Card>, score: Int) {
        println("$name 카드: ${cards.joinToString(SEPARATOR) { it.toText() }} - 결과: $score")
    }

    fun printResults(results: PlayerResults) {
        println("## 최종 승패")
        printDealerResult(results.getDealerResult())
        results.get().forEach { (name, result) -> printPlayerResult(name, result) }
    }

    private fun printDealerResult(result: Map<GameResult, Int>) {
        println("딜러: ${result[GameResult.WIN]}승 ${result[GameResult.DRAW]}무 ${result[GameResult.LOSE]}패")
    }

    private fun printPlayerResult(name: String, result: GameResult) {
        when (result) {
            GameResult.WIN -> println("$name: 승")
            GameResult.DRAW -> println("$name: 무")
            GameResult.LOSE -> println("$name: 패")
        }
    }

    private fun Card.toText(): String = "${number.toText()}${suit.toText()}"

    private fun CardNumber.toText(): String = when (this) {
        CardNumber.ACE -> "A"
        CardNumber.TWO -> "2"
        CardNumber.THREE -> "3"
        CardNumber.FOUR -> "4"
        CardNumber.FIVE -> "5"
        CardNumber.SIX -> "6"
        CardNumber.SEVEN -> "7"
        CardNumber.EIGHT -> "8"
        CardNumber.NINE -> "9"
        CardNumber.TEN -> "10"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
    }

    private fun Suit.toText(): String = when (this) {
        Suit.SPADE -> "스페이드"
        Suit.HEART -> "하트"
        Suit.DIAMOND -> "다이아몬드"
        Suit.CLOVER -> "클로버"
    }

    fun printInterval() = println()
}
