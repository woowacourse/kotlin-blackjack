package blackjack.view

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.participants.FinalProfit
import blackjack.domain.participants.User
import blackjack.domain.participants.UsersBettingMoney
import blackjack.domain.result.Outcome

class OutputView {
    fun outputInitState(blackJack: BlackJack) {
        print("\n딜러와 ${blackJack.guests.map { it.name }.joinToString(", ")} 에게 2장의 나누었습니다.")
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
        val cardText = user.cards.cards.toList()[0].let { card -> card.value.pattern() + card.mark.name() }
        print("\n${user.name}카드: $cardText")
    }

    fun outputCard(user: User) {
        val cardText = user.cards.cards.joinToString(", ") { card -> card.value.pattern() + card.mark.name() }
        print("\n${user.name}카드: $cardText")
    }

    fun outputDealerDraw() {
        println("\n\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private fun outputScore(user: User) {
        print(" - 결과: ${user.getScore()}")
    }

    private fun outputOutcomes(blackJack: BlackJack) {
        blackJack.run {
            println("\n## 최종 승패")
            println("${dealer.name}: ${getResult().count { it == Outcome.LOSE }}승 ${getResult().count { it == Outcome.WIN }}패")
            guests.forEachIndexed { index, user -> outputOutcome(user, getResult()[index]) }
        }
    }

    private fun outputOutcome(user: User, outcome: Outcome) {
        when (outcome) {
            Outcome.WIN, Outcome.BLACKJACK -> "승"
            Outcome.DRAW -> "무"
            Outcome.LOSE -> "패"
        }.let { println("${user.name}: $it") }
    }

    fun outputProfits(blackJack: BlackJack, usersBettingMoney: UsersBettingMoney) {
        blackJack.run {
            val finalProfit = FinalProfit.playersFinalProfits(dealer, usersBettingMoney)
            println("\n## 최종 수익")
            println("${dealer.name}: ${finalProfit.getDealerProfit()}")
            guests.forEach { user ->
                outputUserProfits(
                    user,
                    finalProfit,
                )
            }
        }
    }

    fun outputUserProfits(user: User, finalProfit: FinalProfit) {
        val profit = finalProfit.getUserProfit(user)
        println("${user.name}: $profit")
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
            CardValue.TWO,
            CardValue.THREE,
            CardValue.FOUR,
            CardValue.FIVE,
            CardValue.SIX,
            CardValue.SEVEN,
            CardValue.EIGHT,
            CardValue.NINE,
            CardValue.TEN,
            -> this.value.toString()
        }
}
