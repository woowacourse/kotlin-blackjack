package blackjack.view

import blackjack.domain.model.Player

class OutputView {
    fun showDistributeCardMessage(players: Collection<Player>) {
        val joinedNames = players.joinToString { it.name }
        println(DISTRIBUTE_CARD_MESSAGE.format(joinedNames))
    }

    companion object {
        private const val DISTRIBUTE_CARD_MESSAGE = "딜러와 %s에게 각각 2장의 카드를 나누었습니다."
    }
}
