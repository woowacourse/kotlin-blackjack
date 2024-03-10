package blackjack.view

import Player
import blackjack.model.game.State.Finished
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val GAME_SETTING = "딜러와 %s에게 2장을 나누었습니다."
const val DEALER_CARD = "딜러: %s"
const val PLAYER_CARD = "%s 카드: %s"
const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."

fun showPlayerEntry(names: String) {
    println("\n$GAME_SETTING".format(names))
}

fun showHands(
    dealer: Dealer,
    playerEntry: PlayerEntry,
) {
    println()
    showDealerOneHand(dealer)
    showPlayersHand(playerEntry)
}

fun showPlayerHand(player: Player) {
    println("$PLAYER_CARD${getPlayerState(player)}".format(player.name, player.hand.cards.joinToString()))
}

fun showDealerDrawMessage(dealer: Dealer) {
    println("\n$DEALER_DRAW_MESSAGE")
    showDealerHand(dealer)
}

private fun showPlayersHand(playerEntry: PlayerEntry) {
    playerEntry.players.forEach { player ->
        showPlayerHand(player)
    }
}

private fun showDealerOneHand(dealer: Dealer) {
    dealer.hand.cards.getOrNull(0)?.let {
        println(DEALER_CARD.format(it))
    } ?: println("딜러 카드 정보 없음")
}

private fun showDealerHand(dealer: Dealer) {
    println("$DEALER_CARD${getPlayerState(dealer)}".format(dealer.hand.cards.joinToString()))
}

private fun getPlayerState(player: Player): String =
    when (player.state) {
        Finished.Bust -> " (Bust)"
        Finished.BlackJack -> " (BlackJack)"
        else -> ""
    }

private fun getPlayerState(dealer: Dealer): String =
    when (dealer.state) {
        Finished.Bust -> " (Bust)"
        Finished.BlackJack -> " (BlackJack)"
        else -> ""
    }
