package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.GameResultType
import blackjack.model.result.PlayersResult

class Dealer : Role() {
    override fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    fun takeTurn(
        cardProvider: CardProvider,
        endRoundAction: () -> Unit,
    ) {
        while (decideMoreCard()) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction()
        }
    }

    fun calculateGameResult(players: Players): GameResultStorage {
        val dealerResult = DealerResult()
        val playersResult = PlayersResult()

        players.playerGroup.forEach { player ->
            val gameResultType = decideGameResultType(player)
            dealerResult.add(gameResultType)
            playersResult.add(player.name, gameResultType.reverse())
        }
        return GameResultStorage(dealerResult, playersResult)
    }

    private fun decideGameResultType(player: Player): GameResultType {
        val cardSum = getCardSum()
        val playerCardSum = player.getCardSum()

        return when {
            player.isBurst() -> GameResultType.WIN
            isBurst() -> GameResultType.LOSE
            cardSum > playerCardSum -> GameResultType.WIN
            cardSum == playerCardSum -> GameResultType.DRAW
            else -> GameResultType.LOSE
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
