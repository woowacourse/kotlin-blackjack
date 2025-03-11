package blackjack.view

import blackjack.domain.model.card.Card
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.Player
import blackjack.domain.model.progress.Rule
import blackjack.domain.model.progress.WinLoss
import java.util.Locale

class OutputView(
    private val locale: Locale,
) {
    fun showDistributeCardMessage(participants: List<GameParticipant>) {
        val joinedNames = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showDealerFirstCardsInfo(dealer: Dealer) {
        val name = dealer.name
        val firstCard = dealer.handCards.getCardByIndex(0)

        println(CARD_INFO_MESSAGE.format(name, makeCardText(firstCard)))
    }

    fun showPlayerCardsInfo(player: Player) {
        println(makeParticipantInfoText(player))
    }

    fun showDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
    }

    fun showCardsResult(participants: List<GameParticipant>) {
        participants.forEach {
            println(makeParticipantInfoText(it) + CARD_RESULT_MESSAGE + Rule.calculateResultByCards(it.showCards()))
        }
    }

    private fun makeParticipantInfoText(participant: GameParticipant): String {
        val name = participant.name
        val cardsInfoText = participant.handCards.currentCards().joinToString { makeCardText(it) }
        return CARD_INFO_MESSAGE.format(name, cardsInfoText)
    }

    private fun makeCardText(card: Card): String = card.number.initial + SuitTranslator.localize(card.suit, locale)

    fun showFinalResult(
        dealerWinLossText: String,
        playersWinLoss: List<Pair<Player, WinLoss>>,
    ) {
        println("## 최종 승패")
        println("딜러: " + dealerWinLossText)
        playersWinLoss.forEach({ (player, winLoss) ->
            println(player.name + ": " + winLoss.koreanText)
        })
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val CARD_INFO_MESSAGE = "%s카드: %s"
        private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val CARD_RESULT_MESSAGE = " - 결과: "
    }
}
