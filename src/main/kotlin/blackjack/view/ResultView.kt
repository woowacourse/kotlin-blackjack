package blackjack.view

import blackjack.model.game.State
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

const val DEALER_CARD_RESULT = "딜러: %s - 결과: %d"
const val PLAYER_CARD_RESULT = "%s카드: %s - 결과: %d"
const val FINAL_WIN_OR_LOSS = "## 최종 승패"

fun showHandsScore(
    dealer: Dealer,
    playerEntry: PlayerEntry,
) {
    showDealerScore(dealer)
    showPlayersScore(playerEntry)
}

fun showFinalWinOrLoss() {
    println(FINAL_WIN_OR_LOSS)
}

private fun showDealerScore(dealer: Dealer) {
    val state =
        when (dealer.state) {
            State.BUST -> " (Bust)"
            State.BLACKJACK -> " (BlackJack)"
            else -> ""
        }

    println()
    println(
        DEALER_CARD_RESULT.format(
            dealer.hand.cards.joinToString(),
            dealer.hand.totalScore,
        ) + state,
    )
}

private fun showPlayersScore(playerEntry: PlayerEntry) {
    println()
    playerEntry.players.forEach { player ->
        val state =
            when (player.state) {
                State.BUST -> " (Bust)"
                State.BLACKJACK -> " (BlackJack)"
                else -> ""
            }

        println(
            PLAYER_CARD_RESULT.format(
                player.name,
                player.hand.cards.joinToString(),
                player.hand.totalScore,
            ) + state,
        )
    }
}
