package blackjack.view

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.participants.User
import blackjack.domain.result.BlackJackResult
import blackjack.domain.result.BlackJackResultUser

class OutputView {
    fun outputInitState(blackJack: BlackJack) {
        print("\n딜러와 ${blackJack.guests.map{it.name}.joinToString(", ")} 에게 2장의 나누었습니다.")
        outputCardForDealer(blackJack.dealer)
        blackJack.guests.forEach { user ->
            outputCard(user)
        }
    }

    fun outputResult(blackJack: BlackJack) {
        blackJack.participants.all().forEach { user ->
            outputCard(user)
            outputScore(user)
        }
        println("")
        outputOutcomes(BlackJackResult(blackJack.participants))
    }

    fun outputCard(user: User) {
        val cardText = user.cards.toList().joinToString(", ") { it.value.pattern() + it.mark.name() }
        print("\n${user.name}카드: $cardText")
    }

    fun outputDealerDraw() { println("\n\n딜러는 16이하라 한장의 카드를 더 받았습니다.") }

    private fun outputCardForDealer(user: User) {
        val cardText = user.cards.toList()[0].let { it.value.pattern() + it.mark.name() }
        print("\n${user.name}카드: $cardText")
    }

    private fun outputScore(user: User) { print(" - 결과: ${user.score}") }

    private fun outputOutcomes(blackJackResult: BlackJackResult) {
        blackJackResult.run {
            println("\n## 최종 승패")
            outputOutcome(dealer)
            guests.forEach { guest -> outputOutcome(guest) }
        }
    }

    private fun outputOutcome(result: BlackJackResultUser) { println("${result.name}: ${result.rateOfReturn}") }

    private fun CardMark.name(): String =
        when (this) {
            CardMark.CLOVER -> "클로버"
            CardMark.DIA -> "다이아몬드"
            CardMark.HEART -> "하트"
            CardMark.SPADE -> "스페이드"
        }

    private fun CardValue.pattern(): String =
        when (this) {
            CardValue.ACE -> "A"
            CardValue.KING -> "K"
            CardValue.QUEEN -> "Q"
            CardValue.JACK -> "J"
            else -> this.value.toString()
        }
}
