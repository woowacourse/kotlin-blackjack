package blackjack.controller

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
        val players = createPlayers(playersNames, deck)
        val dealer = createDealer(deck)
        outputView.showFirstDraw(dealer = dealer.toUiModel(), players = players.map(Participant::toUiModel))
        playGame(players, deck)
        playGame(dealer, deck)
        showGameScore(dealer, players)
        showGameResult(dealer, players)
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerResult = dealer.judge(players).toUiModelWith(dealer.name)
        val playersResult = players.map { it.judge(dealer).toUiModelWith(it.name) }
        outputView.showGameResult(dealerResult, playersResult)
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
            onDraw = deck::draw,
            onDone = outputView::showDealerHitCard,
        )
    }

    private fun playGame(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            player.play(
                onHit = inputView::determineHit,
                onDraw = deck::draw,
                onDone = { outputView.showPlayerHandCards(it.toUiModel()) },
            )
        }
    }

    private fun createPlayers(
        playersNames: List<String>,
        deck: Deck,
    ) = playersNames.map { Player(it, State.Running(Hand(deck.drawMultiple(FIRST_DRAW_CAR_COUNT)))) }

    private fun createDealer(deck: Deck) = Dealer(State.Running(Hand(deck.drawMultiple(FIRST_DRAW_CAR_COUNT))))

    companion object {
        private const val FIRST_DRAW_CAR_COUNT = 2
    }
}
