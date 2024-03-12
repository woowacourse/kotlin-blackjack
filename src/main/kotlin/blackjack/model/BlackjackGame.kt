package blackjack.model

import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.PlayersResult

object BlackjackGame {
    fun calculateGameResult(
        dealer: Dealer,
        players: Players,
    ): GameResultStorage {
        val dealerResult = DealerResult()
        val playersResult = PlayersResult()

        players.playerGroup.forEach { player ->
            val gameResultType = dealer.decideGameResultType(player)
            dealerResult.add(gameResultType)
            playersResult.add(player.name, gameResultType.reverse())
        }
        return GameResultStorage(dealerResult, playersResult)
    }
}
