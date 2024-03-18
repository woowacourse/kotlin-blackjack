package blackjack.view

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.model.Suit

object OutputView {
    fun printInitialResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println(buildDealerInitialCard(dealer))
        players.forEach {
            printCards(it)
        }
    }

    fun printResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println()
        printParticipantStatusWithSum(dealer)
        players.forEach {
            printParticipantStatusWithSum(it)
        }
    }

    private fun printParticipantStatusWithSum(participant: Participant) {
        println(buildParticipantCards(participant) + " - 결과: " + participant.getCardSum())
    }

    fun printCards(participant: Participant) {
        println(buildParticipantCards(participant))
    }

    private fun buildDealerInitialCard(dealer: Dealer): String {
        val name = dealer.name
        val card = listOf(dealer.showCard()[0])
        return buildParticipantCardsString(name, card)
    }

    private fun buildParticipantCards(participant: Participant): String {
        val name = participant.name
        val cards = participant.showCard()
        return buildParticipantCardsString(name, cards)
    }

    private fun buildParticipantCardsString(
        name: String,
        cards: List<Card>,
    ): String {
        val cardStrings = cards.joinToString(", ") { it.cardNumber.format() + it.suit.format() }
        return "${name}카드: $cardStrings"
    }

    private fun CardNumber.format(): String {
        return when (this) {
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
    }

    private fun Suit.format(): String {
        return when (this) {
            Suit.HEART -> "하트"
            Suit.CLOVER -> "클로버"
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
        }
    }

    fun printBlackJackMessage(participant: Participant) {
        println("${participant.name}는 블랙잭 입니다.")
    }

    fun printDealerHitMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printFinalBetAmountMessage() {
        println("\n## 최종 수익")
    }

    fun printAmountResult(amountStatistics: Map<String, Long>) {
        amountStatistics.forEach { participant ->
            println("${participant.key}: ${participant.value}")
        }
    }

    fun printExceptionMessage(message: String?) {
        println(message ?: "알 수 없는 오류가 발생했습니다.")
    }
}
