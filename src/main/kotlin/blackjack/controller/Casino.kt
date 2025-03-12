package blackjack.controller

import blackjack.domain.generator.CardsGenerator
import blackjack.domain.model.BetAmount
import blackjack.domain.model.GameResult
import blackjack.domain.model.Scoreboard
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participant
import blackjack.domain.model.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Casino(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val deck: Deck = Deck(cardsGenerator)
        val players: List<Player> = setPlayers()
        val dealer: Dealer = Dealer()
        initialCardsDistribute(players + dealer, deck)
        outputParticipantCardsInfo(dealer, players)

        runPlayersDrawPhase(players, deck)
        runDealerPhase(dealer, deck)
        outputFinalResult(dealer, players)
        outputParticipantsProfit(dealer, players)
    }

    private fun setPlayers(): List<Player> {
        val rawInputNames = inputView.readPlayerNames()
        return rawInputNames.map {
            Player(name = it, betAmount = setBetAmount(it))
        }
    }

    private fun setBetAmount(playerName: String): BetAmount {
        val input: Double = inputView.readBetAmount(playerName)
        return runCatching {
            BetAmount(input)
        }.getOrElse {
            setBetAmount(playerName)
        }
    }

    private fun initialCardsDistribute(
        participants: List<Participant>,
        deck: Deck,
    ) {
        participants.forEach { participant ->
            participant.drawCard(drawSafely(2, deck))
        }
    }

    private fun drawSafely(
        number: Int,
        deck: Deck,
    ): List<Card> {
        val drawnDeck = deck.pop(number)
        if (drawnDeck == null) {
            deck.refill()
            return drawSafely(number, deck)
        }
        return drawnDeck
    }

    private fun refillDeck(deck: Deck) {
        deck.refill()
    }

    private fun outputParticipantCardsInfo(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showDistributeCardMessage(players)
        outputView.showDealerCardsInfo(dealer)
        players.forEach { outputView.showPlayerCardsInfo(it) }
        outputView.newLine()
    }

    private fun runPlayersDrawPhase(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach {
            runPlayerPhase(it, deck)
        }
    }

    private fun runPlayerPhase(
        player: Player,
        deck: Deck,
    ) {
        while (player.isDrawable()) {
            val response = inputView.readWantExtraCard(player.name)
            if (!response) {
                outputView.showPlayerCardsInfo(player)
                break
            }
            player.drawCard(drawSafely(1, deck))
            outputView.showPlayerCardsInfo(player)
        }
        outputView.newLine()
    }

    private fun runDealerPhase(
        dealer: Dealer,
        deck: Deck,
    ) {
        while (dealer.isDrawable()) {
            dealer.drawCard(drawSafely(1, deck))
            outputView.showDealerDrawMessage()
        }
        outputView.newLine()
    }

    private fun outputParticipantsProfit(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val scoreboard = Scoreboard(dealer, players)
        val dealerProfit: Double = scoreboard.getDealerProfit()
        val playersProfit: Map<Player, Double> = scoreboard.getPlayersProfit()

        outputView.showProfitResult(dealerProfit, playersProfit)
    }

    private fun outputFinalResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showCardsResult(listOf(dealer) + players)
        outputView.newLine()

        val finalResult: Map<GameResult, Int> = Scoreboard(dealer, players).getDealerResult()
        outputView.showFinalResult(finalResult, dealer, players)
    }
}
