package blackjack.view

import blackjack.Dealer
import blackjack.Participant
import blackjack.Players
import blackjack.model.domain.Hand

object OutputView {
    private const val MESSAGE_DISTRIBUTION = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_DEALER_HIT = "%s는 16이하라 한장의 카드를 더 받았습니다."
    private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s - 결과: %d"
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 수익"
    private const val MESSAGE_CARD_STATUS = "%s: %d"
    private const val NEW_LINE = "\n"

    fun printInitialStats(
        players: Players,
        dealer: Dealer,
    ) {
        printDistributionMessage(players, dealer)
        println(getDealerCardResult(dealer))
        printPlayerCards(players)
        println()
    }

    fun printDealerHit(participant: Participant) {
        println(MESSAGE_DEALER_HIT.format(participant.name))
    }

    fun printFinalCards(
        players: Players,
        dealer: Dealer,
    ) {
        println()
        println(
            MESSAGE_PARTICIPANT_CARD_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealer.name,
                    dealer.state.hand.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
                ),
                dealer.state.hand.calculateTotal(),
            ),
        )

        players.value.forEach { player ->
            println(
                MESSAGE_PARTICIPANT_CARD_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        player.name,
                        player.state.hand.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
                    ),
                    player.state.hand.calculateTotal(),
                ),
            )
        }
    }

    fun printResult(players: Players, dealer: Dealer) {
        val result = players.value.sumOf { it.state.profit(dealer.state.hand.calculateTotal()) }

        println(MESSAGE_TITLE_RESULT)
        println(MESSAGE_CARD_STATUS.format(dealer.name, result * -1))

        players.value.forEach {
            println(MESSAGE_CARD_STATUS.format(it.name, it.state.profit(dealer.state.hand.calculateTotal())))
        }
    }

    fun printSinglePlayerCards(name: String, hand: Hand) {
        println(
            MESSAGE_CARD_INFO.format(
                name,
                hand.cards.joinToString { "${it.cardRank.symbol}${it.shape.label}" },
            ),
        )
    }

    fun printNewLine() = print(NEW_LINE)

    private fun printDistributionMessage(players: Players, dealer: Dealer) {
        val names = players.value.joinToString { it.name }
        println("\n${MESSAGE_DISTRIBUTION.format(dealer.name, names)})")
    }

    private fun getDealerResult(players: Players, dealer: Dealer): Int =
        players.value.sumOf { it.state.profit(dealer.state.hand.calculateTotal()).unaryMinus() }

    private fun printPlayerCards(players: Players) {
        players.value.forEach {
            printSinglePlayerCards(it.name, it.state.hand)
        }
    }

    private fun getDealerCardResult(dealer: Dealer): String {
        val (shape, cardRank) = dealer.state.hand.cards.first()
        return MESSAGE_CARD_INFO.format(dealer.name, "${cardRank.value}${shape.label}")
    }
}
