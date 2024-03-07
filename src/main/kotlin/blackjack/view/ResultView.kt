package blackjack.view

import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val DEALER_CARD_RESULT = "딜러: %s - 결과: %d"
const val PLAYER_CARD_RESULT = "%s카드: - 결과: %d"

fun showHandsScore(
    dealer: Dealer,
    playerEntry: PlayerEntry,
) {
    showDealerScore(dealer)
    showPlayersScore(playerEntry)
}

private fun showDealerScore(dealer: Dealer) {
    println(DEALER_CARD_RESULT.format(dealer.hand.cards, dealer.hand.totalScore))
}

private fun showPlayersScore(playerEntry: PlayerEntry) {
    playerEntry.players.forEach { player ->
        println(PLAYER_CARD_RESULT.format(player.name, player.hand.totalScore))
    }
}
