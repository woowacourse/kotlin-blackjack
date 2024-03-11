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
        val players = playersNames.map { Player(it, State.Running(Hand(deck.drawMultiple(FIRST_DRAW_CAR_COUNT)))) }
        val dealer = Dealer(State.Running(Hand(deck.drawMultiple(FIRST_DRAW_CAR_COUNT))))

        outputView.showFirstDraw(dealer = dealer.toUiModel(), players = players.map(Participant::toUiModel))

        players.forEach { player ->
            player.play(
                onHit = inputView::determineHit,
                onDraw = deck::draw,
                onDone = { outputView.showPlayerHandCards(it.toUiModel()) },
            )
        }

        dealer.play(
            onDraw = deck::draw,
            onDone = outputView::showDealerHitCard,
        )
        val participants: List<ParticipantUiModel> = (listOf(dealer) + players).map(Participant::toUiModel)
        outputView.showParticipantsScore(participants)

        val dealerResult = dealer.judge(players).toUiModelWith(dealer.name)
        val playersResult = players.map { it.judge(dealer).toUiModelWith(it.name) }
        outputView.showGameResult(dealerResult, playersResult)
    }

    companion object {
        private const val FIRST_DRAW_CAR_COUNT = 2
    }
}
