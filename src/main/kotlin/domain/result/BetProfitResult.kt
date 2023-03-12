package domain.result

import domain.Dealer
import domain.Players
import domain.ProfitMoney

class BetProfitResult(players: Players, dealer: Dealer) {
    val playersResult: PlayersResult = PlayersResult(players, dealer)

    val dealerResult: ParticipantResultInfo =
        ParticipantResultInfo(dealer.name, ProfitMoney(-(playersResult.sum)), dealer.getCards(), dealer.getScore())
}
