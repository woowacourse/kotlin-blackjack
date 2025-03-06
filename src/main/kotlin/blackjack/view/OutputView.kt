package blackjack.view

import blackjack.domain.model.GameParticipant

class OutputView {
    fun showDistributeCardMessage(participants: List<GameParticipant>) {
        val joinedNames = participants.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    fun showCardsInfo(gameParticipant: GameParticipant) {
        val name = gameParticipant.name
        val cardsInfoText = gameParticipant.showCards().joinToString { it.getCardText() }
        println(CARD_INFO_MESSAGE.format(name, cardsInfoText))
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
        private const val CARD_INFO_MESSAGE = "%s카드: %s"
    }
}
