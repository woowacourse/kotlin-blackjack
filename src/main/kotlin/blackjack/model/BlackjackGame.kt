package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.model.participant.Role
import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.PlayersResult

object BlackjackGame {
    private const val INIT_RECEIVE_CARD_COUNT = 2

    fun initCard(
        dealer: Dealer,
        players: Players,
        cardProvider: CardProvider,
    ) {
        dealer.initReceiveCard(cardProvider)
        players.playerGroup.forEach {
            it.initReceiveCard(cardProvider)
        }
    }

    private fun Role.initReceiveCard(cardProvider: CardProvider) {
        receiveCard(Card.provideCards(cardProvider, INIT_RECEIVE_CARD_COUNT))
    }

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
