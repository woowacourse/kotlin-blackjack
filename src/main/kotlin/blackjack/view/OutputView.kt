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

    fun outputCard(user: User) {
        println("## 최종 승패")
        print("${user.name}카드")
        user.cards.toList().forEach {
            print(" ${it.value.value}${it.mark.title}")
        }
    }

    private fun outputScore(user: User) {
        print("- 결과 ${user.score}")
    }
}
