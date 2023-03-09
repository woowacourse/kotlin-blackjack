package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantProfit
import blackjack.domain.data.ParticipantScore
import blackjack.domain.result.GameResult
import blackjack.domain.result.PlayerResults

object OutputView {
    private const val SEPARATOR = ", "

    fun printFirstOpenCards(participantsCards: List<ParticipantCards>) {
        println("${participantsCards.first().name}와 ${participantsCards.drop(1).joinToString(SEPARATOR) { it.name }}에게 2장의 카드를 나누었습니다.")
        participantsCards.forEach { (name, cards) ->
            printCards(name, cards)
        }
    }

    fun printCards(name: String, cards: List<Card>) {
        println("$name 카드: ${cards.joinToString(SEPARATOR) { it.toText() }}")
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printScores(participantsCards: List<ParticipantCards>, scores: List<ParticipantScore>) {
        scores.forEachIndexed { index, (name, score) ->
            printScore(name, participantsCards[index].cards, score)
        }
        printInterval()
    }

    private fun printScore(name: String, cards: List<Card>, score: Int) {
        println("$name 카드: ${cards.joinToString(SEPARATOR) { it.toText() }} - 결과: $score")
    }

    fun printResults(results: PlayerResults) {
        println("## 최종 승패")
        printDealerResult(results.getDealerResult())
        results.get().forEach { playerResult -> printPlayerResult(playerResult.name, playerResult.result) }
        printInterval()
    }

    private fun printDealerResult(result: DealerResult) {
        println("딜러: ${result.win}승 ${result.draw}무 ${result.lose}패")
    }

    private fun printPlayerResult(name: String, result: GameResult) {
        when (result) {
            GameResult.BLACKJACK -> println("$name: 블랙잭")
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

    fun printProfits(profits: List<ParticipantProfit>) {
        println("## 최종 수익")
        profits.forEach { (name, profit) ->
            println("$name: $profit")
        }
    }

    fun printInterval() = println()
}
