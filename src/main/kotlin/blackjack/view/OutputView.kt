package blackjack.view

import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import java.lang.String.format

class OutputView {
    fun printNames(players: List<Player>) {
        println(MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS.format(players.joinToString { it.name }))
    }

    fun printDealerCards(dealerCard: List<TrumpCard>) {
        println(format(MESSAGE_OUTPUT_DEALER_CARD, makeCardListMessage(dealerCard)))
    }

    fun printOneCardMessage(player: Player) {
        println(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards.allCards)))
    }

    fun printPlayerCards(players: List<Player>) {
        players.forEach { player ->
            println(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards.allCards)))
        }
    }

    fun printDealerSum(dealer: Dealer) {
        print(format(MESSAGE_OUTPUT_DEALER_CARD, makeCardListMessage(dealer.cards.allCards)))
        println(format(MESSAGE_OUTPUT_SUM, dealer.cards.finalScore()))
    }

    fun printPlayerSum(players: List<Player>) {
        players.forEach { player ->
            print(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards.allCards)))
            println(format(MESSAGE_OUTPUT_SUM, player.cards.finalScore()))
        }
    }

    fun printDealerProfit(profit: Double) {
        println(MESSAGE_OUTPUT_PROFIT_GUIDE)
        println(format(MESSAGE_OUTPUT_DEALER_PROFIT, profit))
    }

    fun printPlayerProfit(
        name: String,
        profit: Double,
    ) {
        println(format(MESSAGE_OUTPUT_PLAYER_OUTPUT, name, profit))
    }

    private fun makeCardListMessage(cards: List<TrumpCard>): String =
        cards.joinToString { card ->
            cardMessageFormat(card)
        }

    private fun cardMessageFormat(card: TrumpCard): String = MESSAGE_CARD.format(card.tier.toEnglish(), card.shape.toKorean())

    fun printDealerExtraCard(count: Int) {
        println(format(MESSAGE_OUTPUT_DEALER_EXTRA_CARD, count))
    }

    private fun Shape.toKorean(): String =
        when (this) {
            Shape.HEART -> "하트♥\uFE0F"
            Shape.DIA -> "다이아몬드♦\uFE0F"
            Shape.CLOVER -> "클로버♣\uFE0F"
            Shape.SPADE -> "스페이드♠\uFE0F"
        }

    private fun CardTier.toEnglish(): String =
        when (this) {
            CardTier.ACE -> "A"
            CardTier.JACK -> "J"
            CardTier.QUEEN -> "Q"
            CardTier.KING -> "K"
            else -> this.values.toString()
        }

    companion object {
        private const val MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다.\n"
        private const val MESSAGE_OUTPUT_DEALER_EXTRA_CARD = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n"
        private const val MESSAGE_OUTPUT_SUM = " - 결과: %d"
        private const val MESSAGE_OUTPUT_PLAYER_CARD = "%s카드: %s"
        private const val MESSAGE_OUTPUT_DEALER_CARD = "딜러: %s"
        private const val MESSAGE_OUTPUT_PROFIT_GUIDE = "\n## 최종 수익"
        private const val MESSAGE_OUTPUT_DEALER_PROFIT = "딜러: %.2f"
        private const val MESSAGE_OUTPUT_PLAYER_OUTPUT = "%s: %.2f"
        private const val MESSAGE_CARD = "%s%s"
    }
}
