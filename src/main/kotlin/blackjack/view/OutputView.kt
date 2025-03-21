package blackjack.view

import blackjack.domain.model.card.Card
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.bet.ProfitInfo
import java.util.Locale

class OutputView(
    private val locale: Locale,
) {
    fun showDistributeCardMessage(participants: List<GameParticipant>) {
        lineSeparator()
        val joinedNames = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showInitCardInfo(gameParticipants: List<GameParticipant>) {
        gameParticipants.forEach { participant ->
            val name = participant.name
            val cardsInfoText = participant.initCards.joinToString { makeCardText(it) }
            println(CARD_INFO_MESSAGE.format(name, cardsInfoText))
        }
        lineSeparator()
    }

    fun showPlayerCardsInfo(player: Player) {
        println(makeParticipantInfoText(player))
    }

    fun showDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
        lineSeparator()
    }

    fun showCardsResult(participants: List<GameParticipant>) {
        participants.forEach {
            println(makeParticipantInfoText(it) + CARD_RESULT_MESSAGE + it.bestValue)
        }
        lineSeparator()
    }

    fun showParticipantsFinalProfit(profitInfos: List<ProfitInfo>) {
        println(HEADER_FINAL_PROFIT)
        profitInfos.forEach { (name, profit) ->
            println("%s: %.0f".format(name, profit.value))
        }
    }

    fun endDrawPhase() {
        lineSeparator()
    }

    fun showErrorMessage(errorMessage: String) {
        println(errorMessage)
    }

    private fun makeParticipantInfoText(participant: GameParticipant): String {
        val name = participant.name
        val cardsInfoText = participant.cards.joinToString { makeCardText(it) }
        return CARD_INFO_MESSAGE.format(name, cardsInfoText)
    }

    private fun makeCardText(card: Card): String = card.number.initial + Translator.suitLocalize(card.suit, locale)

    private fun lineSeparator() {
        println()
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val CARD_INFO_MESSAGE = "%s카드: %s"
        private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val CARD_RESULT_MESSAGE = " - 결과: "
        private const val HEADER_FINAL_PROFIT = "## 최종 수익"
    }
}
