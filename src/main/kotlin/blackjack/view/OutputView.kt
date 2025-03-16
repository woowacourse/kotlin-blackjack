package blackjack.view

import blackjack.domain.model.card.Card
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.Profit
import blackjack.domain.model.progress.WinLoss
import blackjack.domain.model.progress.WinLossStatistics
import java.util.Locale

class OutputView(
    private val locale: Locale,
) {
    fun lineSeparator() {
        println()
    }

    fun showDistributeCardMessage(participants: List<GameParticipant>) {
        lineSeparator()
        val joinedNames = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showDealerFirstCardsInfo(dealer: Dealer) {
        val name = dealer.name
        val firstCard = dealer.getFirstCard()

        println(CARD_INFO_MESSAGE.format(name, makeCardText(firstCard)))
    }

    fun showPlayersCardsInfo(players: Collection<Player>) {
        players.forEach { player ->
            println(makeParticipantInfoText(player))
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

    private fun makeParticipantInfoText(participant: GameParticipant): String {
        val name = participant.name
        val cardsInfoText = participant.cards.joinToString { makeCardText(it) }
        return CARD_INFO_MESSAGE.format(name, cardsInfoText)
    }

    private fun makeCardText(card: Card): String = card.number.initial + Translator.suitLocalize(card.suit, locale)

    fun showFinalResult(
        winLossStatistics: WinLossStatistics,
        players: List<Player>,
    ) {
        println(HEADER_FINAL_RESULT)
        println(DEALER_RESULT_TEMPLATE.format(makeDealerWinLossText(winLossStatistics)))
        players.forEach { player ->
            println(
                player.name + ": " +
                    Translator.winLossLocalize(
                        winLossStatistics.playerWinLoseInfo[player] ?: throw IllegalArgumentException(ERROR_INVALID_PLAYER),
                        locale,
                    ),
            )
        }
    }

    fun showFinalProfit(participantProfitInfos: List<Pair<GameParticipant, Profit>>) {
        println(HEADER_FINAL_PROFIT)
        participantProfitInfos.forEach { (participant, profit) ->
            println("%s: %.0f".format(participant.name, profit.value))
        }
    }

    fun makeDealerWinLossText(winLossStatistics: WinLossStatistics): String {
        val dealerWinLoss = winLossStatistics.loadDealerResults()

        val winLossTexts =
            WinLoss.entries.mapNotNull { winLoss ->
                dealerWinLoss[winLoss]?.let { value ->
                    value.toString() + Translator.winLossLocalize(winLoss, locale)
                }
            }

        return winLossTexts.joinToString(" ")
    }

    fun endDrawPhase() {
        lineSeparator()
    }

    fun showErrorMessage(errorMessage: String) {
        println(errorMessage)
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val CARD_INFO_MESSAGE = "%s카드: %s"
        private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val CARD_RESULT_MESSAGE = " - 결과: "
        private const val HEADER_FINAL_RESULT = "## 최종 승패"
        private const val HEADER_FINAL_PROFIT = "## 최종 수익"
        private const val DEALER_RESULT_TEMPLATE = "딜러: %s"

        private const val ERROR_INVALID_PLAYER = "플레이어를 찾을 수 없습니다."
    }
}
