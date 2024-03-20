package blackjack.view

import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.config.GameRule.DEALER_MAX_SCORE_FOR_HIT
import blackjack.model.config.GameRule.DEALER_NAME
import blackjack.model.result.Money
import blackjack.model.result.PlayersProfit
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
        println(number.toOutput() + shape.toOutput())
    }

    fun printPlayerCardHand(role: Role) {
        print(NAME_CARD_HAND_FORMAT.format(role.name.name))
        printAllCardHand(role.state.getCardHands())
    }

    fun printDealerHit() = println("\n${DEALER_NAME}는 ${DEALER_MAX_SCORE_FOR_HIT.point}이하라 한 장의 카드를 더 받았습니다.")

    fun printFinalCardHands(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n")

        printPlayerCardHand(dealer)
        println(CARD_HAND_SUM_FORMAT.format(dealer.state.getCardHandScore().point))

        players.players.forEach {
            printPlayerCardHand(it)
            println(CARD_HAND_SUM_FORMAT.format(it.state.getCardHandScore().point))
        }
        println()
    }

    fun printParticipantsProfit(
        dealerProfit: Money,
        playersProfit: PlayersProfit,
    ) {
        println("## 최종 수익")
        println("$DEALER_NAME: ${dealerProfit.amount}")
        playersProfit.result.forEach { (playerName, profit) ->
            println("${playerName.name}: ${profit.amount} ")
        }
    }

    private fun printAllCardHand(cardHand: CardHand) {
        print(cardHand.hand.joinToString { it.number.toOutput() + it.shape.toOutput() })
    }

    companion object {
        private const val NAME_CARD_HAND_FORMAT = "%s 카드: "
        private const val CARD_HAND_SUM_FORMAT = " - 결과: %d"
    }
}

private fun CardNumber.toOutput(): String {
    if (this == CardNumber.ACE) return "A"
    return this.number.toString()
}

private fun CardShape.toOutput(): String =
    when (this) {
        CardShape.HEART -> "하트"
        CardShape.CLOVER -> "클로버"
        CardShape.SPADE -> "스페이드"
        CardShape.DIAMOND -> "다이아몬드"
    }
