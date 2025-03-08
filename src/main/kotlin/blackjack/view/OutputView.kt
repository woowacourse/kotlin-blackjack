package blackjack.view

import blackjack.domain.card.Tier
import blackjack.domain.GameResult
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import java.lang.String.format

class OutputView {
    fun printNames(players: List<Player>) {
        println(MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS.format(players.joinToString { it.name }))
    }

    fun printDealerCards(dealerCard: TrumpCard) {
        println(format(MESSAGE_OUTPUT_DEALER_CARD, cardMessageFormat(dealerCard)))
    }

    fun printOneCardMessage(player: Player) {
        println(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards)))
    }

    fun printPlayerCards(players: List<Player>) {
        players.forEach { player ->
            println(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards)))
        }
    }

    fun printDealerSum(participants: Dealer) {
        print(format(MESSAGE_OUTPUT_DEALER_CARD, makeCardListMessage(participants.cards)))
        println(format(MESSAGE_OUTPUT_SUM, participants.sum()))
    }

    fun printPlayerSum(players: List<Player>) {
        players.forEach { player ->
            print(format(MESSAGE_OUTPUT_PLAYER_CARD, player.name, makeCardListMessage(player.cards)))
            println(format(MESSAGE_OUTPUT_SUM, player.sum()))
        }
    }

    fun printDealerResult(result: Map<GameResult, Int>) {
        println(MESSAGE_OUTPUT_RESULT_GUIDE)
        val (playerWin, playerLose) = result.map { it.value }
        println(format(MESSAGE_OUTPUT_DEALER_RESULT, playerLose, playerWin))
    }

    fun printPlayerResult(
        name: String,
        result: GameResult,
    ) {
        println(format(MESSAGE_OUTPUT_PLAYER_RESULT, name, result.toKorean()))
    }

    private fun makeCardListMessage(cards: List<TrumpCard>): String =
        cards.joinToString { card ->
            cardMessageFormat(card)
        }

    private fun cardMessageFormat(card: TrumpCard): String = MESSAGE_CARD.format(card.tier.toEnglish(), card.shape.toKorean())

    fun printDealerExtraCard(count: Int) {
        println(format(MESSAGE_OUTPUT_DEALER_EXTRA_CARD, count))
    }

    fun printErrorMessage(message: String?) {
        println(message)
    }

    private fun Shape.toKorean(): String =
        when (this) {
            Shape.HEART -> "하트"
            Shape.DIA -> "다이아몬드"
            Shape.CLOVER -> "클로버"
            Shape.SPADE -> "스페이드"
        }

    private fun Tier.toEnglish(): String =
        when (this) {
            Tier.ACE -> "A"
            Tier.JACK -> "J"
            Tier.QUEEN -> "Q"
            Tier.KING -> "K"
            else -> this.values.toString()
        }

    private fun GameResult.toKorean(): String =
        when (this) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
        }

    companion object {
        private const val MESSAGE_OUTPUT_PLAYER_NAME_AND_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다.\n"
        private const val MESSAGE_OUTPUT_DEALER_EXTRA_CARD = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n"
        private const val MESSAGE_OUTPUT_SUM = " - 결과: %d"
        private const val MESSAGE_OUTPUT_PLAYER_CARD = "%s카드: %s"
        private const val MESSAGE_OUTPUT_DEALER_CARD = "딜러: %s"
        private const val MESSAGE_OUTPUT_RESULT_GUIDE = "\n## 최종 승패"
        private const val MESSAGE_OUTPUT_DEALER_RESULT = "딜러: %d승 %d패"
        private const val MESSAGE_OUTPUT_PLAYER_RESULT = "%s: %s"
        private const val MESSAGE_CARD = "%s%s"
    }
}
