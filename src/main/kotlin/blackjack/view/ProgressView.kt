package blackjack.view

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val GAME_SETTING = "딜러와 %s에게 2장을 나누었습니다."
const val DEALER_CARD = "딜러: %s"
const val PLAYER_CARD = "%s카드: %s"
const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."

fun setGame(names: String) {
    println(GAME_SETTING.format(names))
}

fun showHands(
    dealer: Dealer,
    playerEntry: PlayerEntry,
) {
    showDealerHand(dealer)
    showPlayersHand(playerEntry)
}

fun showPlayerHand(player: Player) {
    println(PLAYER_CARD.format(player.name, player.hand.cards.joinToString(", ")))
}

fun showDealerDrawMessage() {
    println(DEALER_DRAW_MESSAGE)
}

private fun showPlayersHand(playerEntry: PlayerEntry) {
    playerEntry.players.forEach { player ->
        showPlayerHand(player)
    }
}

private fun showDealerHand(dealer: Dealer) {
    println(DEALER_CARD.format(dealer.hand.cards[0]))
}
