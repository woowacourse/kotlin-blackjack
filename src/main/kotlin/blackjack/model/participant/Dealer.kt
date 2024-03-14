package blackjack.model.participant

import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.GameResultType
import blackjack.model.result.PlayersResult

class Dealer : Role() {
    override fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    fun calculateGameResult(players: Players): GameResultStorage {
        val dealerResultList = mutableListOf<GameResultType>()
        val playersResult = PlayersResult()

        players.playerGroup.forEach { player ->
            val gameResultType = this.decideGameResultType(player)
            dealerResultList.add(gameResultType)
            playersResult.add(player.name, gameResultType.reverse())
        }
        return GameResultStorage(DealerResult(dealerResultList), playersResult)
    }

    private fun decideGameResultType(player: Player): GameResultType {
        return when {
            player.isBurst() -> GameResultType.WIN
            isBurst() -> GameResultType.LOSE
            getCardSum() > player.getCardSum() -> GameResultType.WIN
            getCardSum() == player.getCardSum() -> GameResultType.DRAW
            else -> GameResultType.LOSE
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
