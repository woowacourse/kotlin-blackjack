package blackjack.view

import blackjack.domain.Outcome
import blackjack.domain.User

class OutputView {
    fun outputInitState(dealer: User, users: List<User>) {
        println("\n딜러와 ${users.map{it.name}} 에게 2장의 나누었습니다.")
        outputCard(dealer)
        println("")

        users.forEach { user ->
            outputCard(user)
            println("")
        }
    }

    fun outputResult(dealer: User, users: List<User>, outcomes: List<Outcome>) {
        println("")
        outputCard(dealer)
        outputScore(dealer)
        println("")

        users.forEach { user ->
            outputCard(user)
            outputScore(user)
            println("")
        }
        println("")
        outputOutcomes(dealer, users, outcomes)
    }

    fun outputCard(user: User) {
        print("${user.name}카드")
        user.cards.toList().forEach {
            print(" ${it.value.value}${it.mark.title}")
        }
    }

    private fun outputScore(user: User) {
        print("- 결과 ${user.score}")
    }

    private fun outputOutcomes(dealer: User, users: List<User>, outcomes: List<Outcome>) {
        println("## 최종 승패")
        println("${dealer.name}: ${outcomes.count{ it == Outcome.LOSE}}승 ${outcomes.count{ it == Outcome.WIN}}패")
        users.forEachIndexed { index, user -> outputOutcome(user, outcomes[index]) }
    }

    fun outputOutcome(user: User, outcome: Outcome) {
        when (outcome) {
            Outcome.WIN -> "승"
            Outcome.DRAW -> "무"
            Outcome.LOSE -> "패"
        }.let { println("${user.name}: $it") }
    }
}
