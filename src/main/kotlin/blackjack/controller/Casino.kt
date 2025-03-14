package blackjack.controller

import blackjack.domain.generator.CardsGenerator
import blackjack.domain.model.GameResult
import blackjack.domain.model.Scoreboard
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.participant.BetAmount
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerBetInfo
import blackjack.view.InputView
import blackjack.view.OutputView

class Casino(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val deck: Deck = Deck(cardsGenerator)
        val participants: Participants = Participants(Dealer(), setPlayers())
        val playerBetInfos: List<PlayerBetInfo> = setPlayerBetInfos(participants.players)
        initialCardsDistribute(participants, deck)
        outputParticipantCardsInfo(participants)

        runPlayersDrawPhase(participants.players, deck)
        runDealerPhase(participants.dealer, deck)
        outputFinalResult(participants)
        outputParticipantsProfit(participants, playerBetInfos)
    }

    private fun setPlayerBetInfos(players: List<Player>): List<PlayerBetInfo> {
        return players.map {
            val betAmount = setBetAmount(it.name)
            PlayerBetInfo(it, betAmount)
        }
    }

    private fun setPlayers(): List<Player> {
        val rawInputNames = inputView.readPlayerNames()
        return rawInputNames.map(::Player)
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
        participants: Participants,
        deck: Deck,
    ) {
        participants.dealer.drawCard(drawSafely(1, deck))
        participants.players.forEach { participant ->
            participant.drawCard(drawSafely(2, deck))
        }
    }

    private fun drawSafely(
        number: Int,
        deck: Deck,
    ): List<Card> {
        return deck.pop(number) ?: run {
            deck.refill()
            drawSafely(number, deck)
        }
    }

    private fun outputParticipantCardsInfo(participants: Participants) {
        outputView.showDistributeCardMessage(participants.players)
        outputView.showDealerCardsInfo(participants.dealer)
        participants.players.forEach { outputView.showPlayerCardsInfo(it) }
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
        dealer.drawCard(drawSafely(1, deck))
        while (dealer.isDrawable()) {
            dealer.drawCard(drawSafely(1, deck))
            outputView.showDealerDrawMessage()
        }
        outputView.newLine()
    }

    private fun outputParticipantsProfit(
        participants: Participants,
        playerBetInfos: List<PlayerBetInfo>,
    ) {
        val scoreboard = Scoreboard(participants)
        val dealerProfit: Double = scoreboard.getDealerProfit(playerBetInfos)
        val playersProfit: Map<Player, Double> = scoreboard.getPlayersProfit(playerBetInfos)

        outputView.showProfitResult(dealerProfit, playersProfit)
    }

    private fun outputFinalResult(participants: Participants) {
        outputView.showCardsResult(participants)
        outputView.newLine()

        val finalResult: Map<GameResult, Int> = Scoreboard(participants).getDealerResult()
        outputView.showFinalResult(finalResult, participants)
    }
}
