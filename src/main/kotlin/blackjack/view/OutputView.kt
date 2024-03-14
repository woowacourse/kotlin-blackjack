package blackjack.view

import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.result.DealerWinning
import blackjack.model.result.PlayerWinning
import blackjack.model.role.Dealer
import blackjack.model.role.Players
import blackjack.model.role.Role

class OutputView {
    fun printInitialDealing(
        dealer: Dealer,
        players: Players,
    ) {
        printInitialDealingPrompt(dealer, players)
        printInitialCardHands(dealer, players)
    }

    private fun printInitialDealingPrompt(
        dealer: Dealer,
        players: Players,
    ) {
        println(dealer.name.name + "와 " + players.players.joinToString { it.name.name } + "에게 2장씩 카드를 나눴습니다")
    }

    private fun printInitialCardHands(
        dealer: Dealer,
        players: Players,
    ) {
        print(NAME_CARD_HAND_FORMAT.format(dealer.name.name))
        printFirstCardHand(dealer.state.getCardHands())

        players.players.forEach {
            print(NAME_CARD_HAND_FORMAT.format(it.name.name))
            printAllCardHand(it.state.getCardHands())
            println()
        }
    }

    private fun printFirstCardHand(cardHand: CardHand) {
        val (shape, number) = cardHand.hand.first()
        println(number.number.toString() + shape.shape)
    }

    fun printPlayerCardHand(role: Role) {
        print(NAME_CARD_HAND_FORMAT.format(role.name.name))
        printAllCardHand(role.state.getCardHands())
    }

    fun printDealerHit() = println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")

    fun printFinalCardHands(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n")

        printPlayerCardHand(dealer)
        println(CARD_HAND_SUM_FORMAT.format(dealer.state.getCardHands().calculateScore()))

        players.players.forEach {
            printPlayerCardHand(it)
            println(CARD_HAND_SUM_FORMAT.format(it.state.getCardHands().calculateScore()))
        }
    }

    fun printWinningResult(
        dealerWinning: DealerWinning,
        playerWinning: PlayerWinning,
    ) {
        println("\n## 최종 승패")
        printDealerWinningResult(dealerWinning)
        printPlayerWinningResult(playerWinning)
    }

    private fun printPlayerWinningResult(playerWinning: PlayerWinning) {
        playerWinning.result.forEach { (name, status) ->
            println("${name.name}: ${status.output}")
        }
        println()
    }

    private fun printDealerWinningResult(dealerWinning: DealerWinning) {
        print("딜러: ")
        dealerWinning.result.forEach {
            print(it.value.toString() + it.key.output + " ")
        }
        println()
    }

    private fun printAllCardHand(cardHand: CardHand) {
        print(cardHand.hand.joinToString { it.number.toOutput() + it.shape.shape })
    }

    private fun CardNumber.toOutput(): String {
        if (this == CardNumber.ACE) return "A"
        return this.number.toString()
    }

    companion object {
        private const val NAME_CARD_HAND_FORMAT = "%s 카드: "
        private const val CARD_HAND_SUM_FORMAT = " - 결과: %d"
    }
}
