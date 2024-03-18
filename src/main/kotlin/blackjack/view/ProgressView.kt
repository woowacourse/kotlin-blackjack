package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players

class ProgressView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        println(INIT_CARD_MESSAGE.format(players.gamePlayers.joinToString(SPLIT_DELIMITER) { it.name }))
        println(DEALER_FIRST_CARD_MESSAGE.format(dealer.getFirstCard()))
        players.gamePlayers.forEach { player ->
            println(PLAYER_CARDS_MESSAGE.format(player.name, player.getAllCards()))
        }
    }

    fun printPlayerCard(player: Player) {
        println(PLAYER_CARDS_MESSAGE.format(player.name, player.getAllCards()))
    }

    fun printBustMessage() {
        println(BUST_MESSAGE)
    }

    fun printDealerAddCard() {
        println(DEALER_ADD_CARD_MESSAGE)
    }

    companion object {
        private const val SPLIT_DELIMITER = ", "
        private const val INIT_CARD_MESSAGE = "\n딜러와 %s명의 플레이어에게 2장의 카드를 나누었습니다."
        private const val DEALER_FIRST_CARD_MESSAGE = "딜러: %s"
        private const val PLAYER_CARDS_MESSAGE = "%s 카드: %s"
        private const val BUST_MESSAGE = "Bust! 더이상 카드를 받을 수 없습니다."
        private const val DEALER_ADD_CARD_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    }
}
