package blackjack.controller

import blackjack.domain.GameResult
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
        val dealer = Dealer(Deck.createDefaultDeck())

        initialDraw(dealer, players)
        processPlayersTurn(dealer, players)
        processDealerTurn(dealer)
        showResult(dealer, players)
    }

    private fun generatePlayers(): List<Player> {
        val playerNames = inputView.getNames()
        return playerNames.map { Player(it) }
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
        while (player.canHit() && inputView.getFlag(player.name)) {
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
    ) {
        outputView.printCardScore(toParticipantsUiModel(dealer, players))
        val result = GameResult.create(dealer, players)
        outputView.printResult(ResultUiModel.create(result))
    }

    private fun toParticipantsUiModel(
        dealer: Dealer,
        players: List<Player>,
    ): List<ParticipantsUiModel> {
        return listOf(ParticipantsUiModel.create(dealer)) + players.map(ParticipantsUiModel::create)
    }
}
