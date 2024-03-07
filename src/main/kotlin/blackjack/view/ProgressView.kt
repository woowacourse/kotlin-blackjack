package blackjack.view

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val GAME_SETTING = "딜러와 %s에게 2장을 나누었습니다."
const val DEALER_CARD = "딜러: %s"
const val PLAYER_CARD = "%s카드: %s"

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

private fun showPlayersHand(playerEntry: PlayerEntry) {
    playerEntry.players.forEach { player ->
        showPlayerHand(player)
    }
}

private fun showDealerHand(dealer: Dealer) {
    println(DEALER_CARD.format(dealer.hand.cards[0]))
}
