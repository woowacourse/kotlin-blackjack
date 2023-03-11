package blackjack.view

import blackjack.domain.blackjack.BlackJackSetting
import blackjack.domain.participants.user.Name
import blackjack.domain.participants.user.User

class OutputView {
    fun outputInitState(blackJackSetting: BlackJackSetting) {
        print("\n딜러와 ${blackJackSetting.guests.map{it.name}.joinToString(", ")} 에게 2장의 나누었습니다.")
        outputCardForDealer(blackJackSetting.dealer)
        blackJackSetting.guests.forEach { user ->
            outputCard(user)
        }
    }

    fun outputResult(blackJackSetting: BlackJackSetting) {
        blackJackSetting.participants.all.forEach { user ->
            outputCard(user)
            outputScore(user)
        }
        println("")
        outputOutcomes(blackJackSetting)
    }

    fun outputCard(user: User) {
        val cardText = user.state.cards.toList().joinToString(", ") { it.toText() }
        print("\n${user.name}카드: $cardText")
    }
    fun outputDealerDraw() { println("\n\n딜러는 16이하라 한장의 카드를 더 받았습니다.") }

    private fun outputCardForDealer(user: User) {
        val cardText = user.state.cards.toList()[0].toText()
        print("\n${user.name}카드: $cardText")
    }

    private fun outputScore(user: User) { print(" - 결과: ${user.score.value}") }

    private fun outputOutcomes(blackJackSetting: BlackJackSetting) {
        blackJackSetting.run {
            println("\n## 최종 승패")
            outputOutcome(dealer.name, dealer.calculateProfit(guests))
            guests.forEach { guest -> outputOutcome(guest.name, guest.calculateProfit(dealer)) }
        }
    }
    private fun outputOutcome(userName: Name, userProfit: Int) { println("$userName: $userProfit") }
}
