package blackjack.view

import blackjack.domain.model.WinLoss
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participant
import blackjack.domain.model.participant.Player

class OutputView {
    fun showDistributeCardMessage(participants: List<Participant>) {
        val joinedNames = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showDealerCardsInfo(dealer: Dealer) {
        val name = dealer.name
        val cardsInfoText = dealer.showFirstCard().getCardText()
        println(CARD_INFO_MESSAGE.format(name, cardsInfoText))
    }

    fun showPlayerCardsInfo(player: Player) {
        println(makeParticipantInfo(player))
    }

    fun showDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
    }

    fun showCardsResult(participants: List<Participant>) {
        participants.forEach {
            println(makeParticipantInfo(it) + CARD_RESULT_MESSAGE + Rule.calculateResultByCards(it.showCards()))
        }
    }

    private fun makeParticipantInfo(participant: Participant): String {
        val name = participant.name
        val cardsInfoText = participant.showCards().joinToString { it.getCardText() }
        return CARD_INFO_MESSAGE.format(name, cardsInfoText)
    }

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
