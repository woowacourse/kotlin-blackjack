package domain.result

import domain.Dealer
import domain.Players

class BetProfitResult(players: Players, dealer: Dealer) {
    val playersResult: PlayersResult = PlayersResult(players, dealer)

    val dealerResult: Int
        get() = playersResult.sum * -1
}
