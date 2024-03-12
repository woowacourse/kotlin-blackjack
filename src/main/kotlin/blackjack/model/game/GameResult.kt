package blackjack.model.game

import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

class GameResult(
    val dealer: Dealer,
    val playerEntry: PlayerEntry,
    val results: List<Result>,
)
