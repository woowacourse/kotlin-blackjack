package blackjack.view

import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.view.model.PlayerUiModel
import java.lang.String.format

class OutputView {
    fun printNames(players: List<Player>) {
        println(MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS.format(players.joinToString { it.name }))
    }

    fun printDealerCards(dealer: Dealer) {
        val cards = dealer.getInitialCards()
        val cardFormat = cards.joinToString { MESSAGE_CARD.format(it.denomination.toEnglish(), it.suit.toKorean()) }
        println(format(MESSAGE_OUTPUT_DEALER_CARD, cardFormat))
    }

    fun printOneCardMessage(player: Player) {
        println(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards.items)))
    }

    fun printPlayerCards(players: List<Player>) {
        players.forEach { player ->
            println(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.getInitialCards())))
        }
    }

    fun printDealerSum(participants: Dealer) {
        print(format(MESSAGE_OUTPUT_DEALER_CARD, makeCardListMessage(participants.cards.items)))
        println(format(MESSAGE_OUTPUT_SUM, participants.totalScore()))
    }

    fun printPlayerSum(players: List<Player>) {
        players.forEach { player ->
            print(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards.items)))
            println(format(MESSAGE_OUTPUT_SUM, player.totalScore()))
        }
    }

    fun printDealerResult(profit: Double) {
        println(MESSAGE_OUTPUT_RESULT_GUIDE)
        println(format(MESSAGE_OUTPUT_DEALER_RESULT, profit))
    }

    fun printPlayerResult(result: List<PlayerUiModel>) {
        result.forEach {
            println(format(MESSAGE_OUTPUT_PLAYER_RESULT, it.name, it.profit))
        }
    }

    private fun makeCardListMessage(cards: Set<TrumpCard>): String =
        cards.joinToString { card ->
            cardMessageFormat(card)
        }

    private fun cardMessageFormat(card: TrumpCard): String = MESSAGE_CARD.format(card.denomination.toEnglish(), card.suit.toKorean())

    fun printDealerExtraCard(count: Int) {
        println(format(MESSAGE_OUTPUT_DEALER_EXTRA_CARD, count))
    }

    fun printErrorMessage(message: String?) {
        println(message)
    }

    private fun Suit.toKorean(): String =
        when (this) {
            Suit.HEART -> "하트"
            Suit.DIA -> "다이아몬드"
            Suit.CLOVER -> "클로버"
            Suit.SPADE -> "스페이드"
        }

    private fun Denomination.toEnglish(): String =
        when (this) {
            Denomination.ACE -> "A"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
            else -> this.values.toString()
        }

    companion object {
        private const val MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다.\n"
        private const val MESSAGE_OUTPUT_DEALER_EXTRA_CARD = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n"
        private const val MESSAGE_OUTPUT_SUM = " - 결과: %d"
        private const val MESSAGE_OUTPUT_PLAYER_CARD = "%s카드: %s"
        private const val MESSAGE_OUTPUT_DEALER_CARD = "딜러: %s"
        private const val MESSAGE_OUTPUT_RESULT_GUIDE = "\n## 최종 승패"
        private const val MESSAGE_OUTPUT_DEALER_RESULT = "딜러: %s"
        private const val MESSAGE_OUTPUT_PLAYER_RESULT = "%s: %s"
        private const val MESSAGE_CARD = "%s%s"
    }
}
