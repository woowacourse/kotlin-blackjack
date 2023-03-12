package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult
import blackjack.domain.result.CardResult
import blackjack.domain.result.MatchResult

object OutputView {
    private const val SEPARATOR = ", "

    fun printFirstOpenCards(cards: Map<String, List<Card>>) {
        println("${cards.keys.first()}와 ${cards.keys.drop(1).joinToString(SEPARATOR)}에게 2장의 카드를 나누었습니다.")
        printCards(cards)
    }

    private fun printCards(cards: Map<String, List<Card>>) {
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

    fun printBlackjackResult(blackJackResult: BlackjackResult) {
        printCardResults(blackJackResult.cardResults)
        printFinalResult(blackJackResult.matchResults)
    }

    private fun printCardResults(cardResults: List<CardResult>) {
        printInterval()
        cardResults.forEach(::printScore)
    }

    private fun printScore(cardResult: CardResult) {
        with(cardResult) {
            println("${participant.name} 카드: ${cards.joinToString(SEPARATOR) { it.toText() }} - 결과: $scoreSum")
        }
    }

    private fun printFinalResult(matchResults: List<MatchResult>) {
        printInterval()
        println("## 최종 수익")
        printMatchResults(matchResults)
    }

    private fun printMatchResults(matchResults: List<MatchResult>) {
        matchResults.forEach(::printResult)
    }

    private fun printResult(matchResult: MatchResult) {
        if (matchResult.participant is Dealer) printDealerResult(matchResult)
        if (matchResult.participant is Player) printPlayerResult(matchResult)
    }

    private fun printDealerResult(result: MatchResult) {
        println("딜러: ${result.total}")
    }

    private fun printPlayerResult(matchResult: MatchResult) {
        val name: String = matchResult.participant.name
        println("$name: ${matchResult.total}")
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
