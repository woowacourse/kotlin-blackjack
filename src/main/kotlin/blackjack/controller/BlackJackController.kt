package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.card.Deck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.uimodel.ParticipantsUiModel
import blackjack.uimodel.ResultUiModel
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun play() {
        val players = generatePlayers()
        val game = generateGame(players)

        setGame(game)
        askHit(game)
        drawToDealer(game)
        showResult(game)
    }

    private fun generatePlayers(): List<Player> {
        val playerNames = inputView.getNames()
        return playerNames.map {
            Player(
                name = it,
                checkHit = (inputView::getFlag),
            )
        }
    }

    private fun generateGame(players: List<Player>): Game {
        val deck = Deck()
        val dealer = Dealer(deck)
        return Game(dealer, players)
    }

    private fun setGame(game: Game) {
        outputView.printDrawMessage(toParticipantsUiModel(game.dealer, game.players))
    }

    private fun askHit(game: Game) {
        game.askHit {
            outputView.printDrawStatus(ParticipantsUiModel.create(it))
        }
    }

    private fun drawToDealer(game: Game) {
        outputView.printDealerDrawMessage(game.processDealerHit())
    }

    private fun showResult(game: Game) {
        outputView.printCardScore(toParticipantsUiModel(game.dealer, game.players))
        val result = game.matchResult()
        outputView.printResult(ResultUiModel.create(result))
    }

    private fun toParticipantsUiModel(
        dealer: Dealer,
        players: List<Player>,
    ): List<ParticipantsUiModel> {
        return listOf(ParticipantsUiModel.create(dealer)) + players.map(ParticipantsUiModel::create)
    }
}
