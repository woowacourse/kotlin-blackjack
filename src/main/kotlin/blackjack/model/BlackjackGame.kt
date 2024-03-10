package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.model.participant.Role
import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.PlayersResult

class BlackjackGame(private val dealer: Dealer, private val players: Players, private val cardProvider: CardProvider) {
    init {
        initDeal()
    }

    private fun initDeal() {
        dealer.initReceiveCard()
        players.playerGroup.forEach {
            it.initReceiveCard()
        }
    }

    private fun Role.initReceiveCard() {
        repeat(INIT_RECEIVE_CARD_COUNT) {
            receiveCard(Card.from(cardProvider))
        }
    }

    fun calculateGameResult(): GameResultStorage {
        val dealerResult = DealerResult()
        val playersResult = PlayersResult()

        players.playerGroup.forEach { player ->
            val gameResultType = dealer.decideGameResultType(player)
            dealerResult.add(gameResultType)
            playersResult.add(player.name, gameResultType.reverse())
        }
        return GameResultStorage(dealerResult, playersResult)
    }

    companion object {
        private const val INIT_RECEIVE_CARD_COUNT = 2
    }
}
