package blackjack.view

import blackjack.domain.BlackJack
import blackjack.domain.CardMark
import blackjack.domain.CardValue
import blackjack.domain.Outcome
import blackjack.domain.User

class OutputView {
    fun outputInitState(blackJack: BlackJack) {
        print("\n딜러와 ${blackJack.users.map{it.name}.joinToString(", ")} 에게 2장의 나누었습니다.")
        outputCardForDealer(blackJack.dealer)
        blackJack.users.forEach { user ->
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

    fun outputCardForDealer(user: User) {
        print("\n${user.name}카드:")
        print(" ${user.cards.toList()[0].value.pattern()}${user.cards.toList()[0].mark.name()}")
    }

    fun outputCard(user: User) {
        print("\n${user.name}카드")
        user.cards.toList().forEach {
            print(" ${it.value.pattern()}${it.mark.name()}")
        }
    }

    fun outputDealerDraw() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private fun outputScore(user: User) {
        print("- 결과 ${user.score}")
    }

    private fun outputOutcomes(blackJack: BlackJack) {
        blackJack.run {
            println("\n## 최종 승패")
            println("${dealer.name}: ${result.count { it == Outcome.LOSE }}승 ${result.count { it == Outcome.WIN }}패")
            users.forEachIndexed { index, user -> outputOutcome(user, result[index]) }
        }
    }

    private fun outputOutcome(user: User, outcome: Outcome) {
        when (outcome) {
            Outcome.WIN -> "승"
            Outcome.DRAW -> "무"
            Outcome.LOSE -> "패"
        }.let { println("${user.name}: $it") }
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
