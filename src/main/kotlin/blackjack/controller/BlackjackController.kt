package blackjack.controller

import blackjack.domain.GameResult
import blackjack.domain.betting.BettingAmount
import blackjack.domain.betting.BettingInfo
import blackjack.domain.card.Deck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player
import blackjack.uimodel.ParticipantsUiModel
import blackjack.uimodel.ResultUiModel
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun play() {
        val players = generatePlayers()
        val bettingInfos = generateBettingInfos(players)
        val dealer = Dealer(Deck.createDefaultDeck())

        initialDraw(dealer, players)
        processPlayersTurn(dealer, players)
        processDealerTurn(dealer)
        showResult(dealer, players, bettingInfos)
    }

    private fun generatePlayers(): List<Player> {
        val playerNames = inputView.getNames()
        return playerNames.map { Player(it) }
    }

    private fun generateBettingInfos(players: List<Player>): List<BettingInfo> {
        return players.map { player ->
            val bettingAmount = BettingAmount(inputView.getBettingAmount(player.name))
            BettingInfo(player, bettingAmount)
        }
    }

    private fun initialDraw(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player ->
            handOutCard(dealer, player)
        }
        handOutCard(dealer, dealer)
        outputView.printDrawMessage(toParticipantsUiModel(dealer, players))
    }

    private fun processPlayersTurn(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player ->
            processEachPlayerTurn(dealer, player)
        }
    }

    private fun processEachPlayerTurn(
        dealer: Dealer,
        player: Player,
    ) {
        while (player.canHit() && inputView.getUserChoice(player.name)) {
            handOutCard(dealer, player)
            outputView.printDrawStatus(ParticipantsUiModel.create(player))
        }
    }

    private fun processDealerTurn(dealer: Dealer) {
        while (dealer.canHit()) {
            handOutCard(dealer, dealer)
            outputView.printDealerDrawMessage()
        }
    }

    private fun handOutCard(
        dealer: Dealer,
        participant: Participant,
    ) {
        repeat(participant.getDrawAmount()) {
            dealer.handOut(participant)
        }
    }

    private fun showResult(
        dealer: Dealer,
        players: List<Player>,
        bettingInfos: List<BettingInfo>,
    ) {
        outputView.printCardScore(toParticipantsUiModel(dealer, players))
        val result = GameResult.create(dealer, players)
        val profits = result.calculateProfits(bettingInfos)
        outputView.printResult(ResultUiModel.create(profits))
    }

    private fun toParticipantsUiModel(
        dealer: Dealer,
        players: List<Player>,
    ): List<ParticipantsUiModel> {
        return listOf(ParticipantsUiModel.create(dealer)) + players.map(ParticipantsUiModel::create)
    }
}
