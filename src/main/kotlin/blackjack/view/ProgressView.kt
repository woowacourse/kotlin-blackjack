package blackjack.view

import Player
import blackjack.model.game.State.Finished
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val GAME_SETTING = "딜러와 %s에게 2장을 나누었습니다."
const val DEALER_CARD = "딜러: %s"
const val PLAYER_CARD = "%s카드: %s"
const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."

fun showPlayerEntry(names: String) {
    println()
    println(GAME_SETTING.format(names))
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
    val state =
        when (player.state) {
            Finished.Bust -> " (Bust)"
            Finished.BlackJack -> " (BlackJack)"
            else -> ""
        }
    println(
        PLAYER_CARD.format(
            player.name,
            player.hand.cards.joinToString(),
        ) + state,
    )
}

fun showDealerDrawMessage(dealer: Dealer) {
    println()
    println(DEALER_DRAW_MESSAGE)
    showDealerHand(dealer)
}

private fun showPlayersHand(playerEntry: PlayerEntry) {
    playerEntry.players.forEach { player ->
        showPlayerHand(player)
    }
}

private fun showDealerOneHand(dealer: Dealer) {
    println(DEALER_CARD.format(dealer.hand.cards[0]))
}

private fun showDealerHand(dealer: Dealer) {
    val state =
        when (dealer.state) {
            Finished.Bust -> " (Bust)"
            Finished.BlackJack -> " (BlackJack)"
            else -> ""
        }
    println(DEALER_CARD.format(dealer.hand.cards.joinToString()) + state)
}
