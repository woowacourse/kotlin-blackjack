package blackjack.controller

import blackjack.model.Betting
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Player
import blackjack.state.State
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ParticipantUiModel
import blackjack.view.toUiModel
import blackjack.view.toUiModelWith

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val playersNames: List<String> = inputView.fetchPlayerNames()
        val deck: Deck = Deck.create()
        val bettings = createBettings(playersNames)
        val players = createPlayers(playersNames, deck, bettings)
        val dealer = createDealer(deck)
        outputView.showFirstDraw(dealer = dealer.toUiModel(), players = players.map(Participant::toUiModel))
        playGame(players, deck)
        playGame(dealer, deck)
        showGameScore(dealer, players)
        showGameResult(dealer, players)
    }

    private fun createBettings(playersNames: List<String>): List<Betting> {
        return playersNames.map { createBetting(it) }
    }

    private tailrec fun createBetting(name: String): Betting {
        return runCatching { inputView.fetchBetting(name) }
            .onFailure {
                outputView.showErrorMessage(it)
                return createBetting(name)
            }
            .getOrThrow()
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerResult = dealer.judge(players).toUiModelWith(dealer.name)
        val playerResults = players.map { it.judge(it.betting, dealer).toUiModelWith(it.name) }
        outputView.showGameResult(listOf(dealerResult) + playerResults)
    }

    private fun showGameScore(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val participants: List<ParticipantUiModel> = (listOf(dealer) + players).map(Participant::toUiModel)
        outputView.showParticipantsScore(participants)
    }

    private fun playGame(
        dealer: Dealer,
        deck: Deck,
    ) {
        dealer.play(
            onDraw = {
                outputView.showDealerHitCard(dealer.toUiModel())
                deck.draw()
            },
            onDone = { outputView.showPlayerHandCards(it.toUiModel()) },
        )
    }

    private fun playGame(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            player.play(
                onDraw = deck::draw,
                onDone = { outputView.showPlayerHandCards(it.toUiModel()) },
            )
        }
    }

    private fun createPlayers(
        playersNames: List<String>,
        deck: Deck,
        betting: List<Betting>,
    ): List<Player> =
        playersNames.zip(betting).map { (name, betting) ->
            Player(
                name = name,
                betting = betting,
                initState = State.Running(Hand(deck.drawMultiple(FIRST_DRAW_CAR_COUNT))),
                onHitCondition = inputView::determineHit,
            )
        }

    private fun createDealer(deck: Deck) =
        Dealer(
            betting = INITIAL_DEALER_BETTING,
            initState =
                State.Running(
                    Hand(deck.drawMultiple(FIRST_DRAW_CAR_COUNT)),
                ),
        )

    companion object {
        private const val FIRST_DRAW_CAR_COUNT = 2
        private val INITIAL_DEALER_BETTING = Betting(0)
    }
}
