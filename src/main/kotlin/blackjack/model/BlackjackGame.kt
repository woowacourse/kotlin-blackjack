package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.participant.Role
import blackjack.model.result.GameResultStorage

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

    fun start(
        readMoreCardDecision: (Player) -> Boolean,
        printPlayerCardsMessage: (Player) -> Unit,
        printDealerAdditionalCardMessage: () -> Unit,
    ): GameResultStorage {
        startPlayersTurn(readMoreCardDecision, printPlayerCardsMessage)
        startDealerTurn(printDealerAdditionalCardMessage)
        val gameResult = dealer.calculateGameResult(players)
        calculateProfit(gameResult)

        return gameResult
    }

    private fun calculateProfit(gameResult: GameResultStorage) {
        gameResult.playersResult.results.forEach { name, result ->
            players.playerGroup.first { it.name == name }.calculateProfit(result)
        }
        dealer.calculateProfit(players)
    }

    private fun startPlayersTurn(
        readMoreCardDecision: (Player) -> Boolean,
        printPlayerCardsMessage: (Player) -> Unit,
    ) {
        players.playerGroup.forEach {
            startPlayerTurn(it, readMoreCardDecision, printPlayerCardsMessage)
        }
    }

    private fun startPlayerTurn(
        player: Player,
        readMoreCardDecision: (Player) -> Boolean,
        printPlayerCardsMessage: (Player) -> Unit,
    ) {
        while (player.decideMoreCard()) {
            val inputMoreCardDecision = readMoreCardDecision(player)
            if (!inputMoreCardDecision) break

            player.receiveCard(Card.from(cardProvider))
            printPlayerCardsMessage(player)
        }
    }

    private fun startDealerTurn(printDealerAdditionalCardMessage: () -> Unit) {
        while (dealer.decideMoreCard()) {
            dealer.receiveCard(Card.from(cardProvider))
            printDealerAdditionalCardMessage()
        }
    }

    companion object {
        private const val INIT_RECEIVE_CARD_COUNT = 2
    }
}
