package blackjack.controller

import blackjack.domain.generator.CardsGenerator
import blackjack.domain.model.GameResult
import blackjack.domain.model.Scoreboard
import blackjack.domain.model.betting.BetAmount
import blackjack.domain.model.betting.BetRecord
import blackjack.domain.model.betting.BetRecords
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
        val deck = Deck(cardsGenerator)
        val dealer = Dealer()
        val players: List<Player> = setPlayers()
        val participants: List<Participant> = listOf(dealer) + players
        val playerBetInfos: List<BetRecord> = setPlayerBetInfos(players)
        initialCardsDistribute(participants, deck)

        runPlayersPhase(players, deck)
        runDealerPhase(dealer, deck)
        outputGameResults(dealer, players)
        outputParticipantsProfit(BetRecords(dealer, playerBetInfos))
    }

    private fun setPlayerBetInfos(players: List<Player>): List<BetRecord> =
        players.map {
            val betAmount = setBetAmount(it.name)
            BetRecord(it, betAmount)
        }

    private fun setPlayers(): List<Player> {
        val names = inputView.readPlayerNames()
        return names.map(::Player)
    }

    private fun setBetAmount(playerName: String): BetAmount {
        val amount: Double = inputView.readBetAmount(playerName)
        return runCatching {
            BetAmount(amount)
        }.getOrElse {
            setBetAmount(playerName)
        }
    }

    private fun initialCardsDistribute(
        participants: List<Participant>,
        deck: Deck,
    ) {
        participants.forEach { participant ->
            participant.draw(drawSafely(2, deck))
        }
        outputView.showParticipantFirstCardsInfo(participants)
        outputView.newLine()
    }

    private fun drawSafely(
        number: Int,
        deck: Deck,
    ): List<Card> =
        deck.pop(number) ?: run {
            deck.refill()
            drawSafely(number, deck)
        }

    private fun runPlayersPhase(
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
                outputView.showParticipantCardsInfo(player)
                break
            }
            player.draw(drawSafely(1, deck))
            outputView.showParticipantCardsInfo(player)
        }
        outputView.newLine()
    }

    private fun runDealerPhase(
        dealer: Dealer,
        deck: Deck,
    ) {
        dealer.draw(drawSafely(1, deck))
        while (dealer.isDrawable()) {
            dealer.draw(drawSafely(1, deck))
            outputView.showDealerDrawMessage()
        }
        outputView.newLine()
    }

    private fun outputGameResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showCardsResult(listOf(dealer) + players)
        outputView.newLine()

        val dealerGameResult: Map<GameResult, Int> = Scoreboard(dealer, players).getDealerResult()
        outputView.showGameResults(dealerGameResult, dealer, players)
    }

    private fun outputParticipantsProfit(betRecords: BetRecords) {
        val dealerProfit: Double = betRecords.dealerProfit()
        val playersProfit: Map<Player, Double> = betRecords.playersProfit()

        outputView.showProfitResults(dealerProfit, playersProfit)
    }
}
