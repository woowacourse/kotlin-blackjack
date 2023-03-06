package blackjack.view

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.participants.Guest
import blackjack.domain.participants.User
import blackjack.domain.result.Outcome

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
        outputOutcomes(blackJack)
    }

    private fun outputCardForDealer(user: User) {
        val cardText = user.cards.toList()[0].let { it.value.pattern() + it.mark.name() }
        print("\n${user.name}카드: $cardText")
    }

    fun outputCard(user: User) {
        val cardText = user.cards.toList().joinToString(", ") { it.value.pattern() + it.mark.name() }
        print("\n${user.name}카드: $cardText")
    }

    fun outputDealerDraw() { println("\n\n딜러는 16이하라 한장의 카드를 더 받았습니다.") }

    private fun outputScore(user: User) { print(" - 결과: ${user.score()}") }

    private fun outputOutcomes(blackJack: BlackJack) {
        blackJack.run {
            println("\n## 최종 승패")
            println("${dealer.name}: ${guests.indices.sumOf { (guests[it].bettingMoney.toInt() * getResult()[it].rate).toInt() * -1 }}")
            guests.indices.forEach { outputOutcome(guests[it], getResult()[it]) }
        }
    }

    private fun outputOutcome(user: Guest, outcome: Outcome) {
        println("${user.name}: ${(user.bettingMoney.toInt() * outcome.rate).toInt()}")
    }

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
