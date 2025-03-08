package blackjack.controller

import blackjack.domain.generator.CardsGenerator
import blackjack.domain.model.GameResult
import blackjack.domain.model.GameResultRecord
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
        val players: List<Player> = inputView.readPlayerNames().map { Player(it) }
        val dealer: Dealer = Dealer()
        initialCardsDistribute(players + dealer, deck)
        outputParticipantCardsInfo(dealer, players)

        runPlayersDrawPhase(players, deck)
        runDealerDrawPhase(dealer, deck)
        outputFinalResult(dealer, players)
    }

    private fun initialCardsDistribute(
        participants: List<Participant>,
        deck: Deck,
    ) {
        participants.forEach { participant ->
            repeat(2) { participant.drawCard(deck) }
        }
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
            runEachPlayerDrawPhase(it, deck)
        }
    }

    private fun runEachPlayerDrawPhase(
        player: Player,
        deck: Deck,
    ) {
        while (player.isDrawable()) {
            val response: Boolean = inputView.readWantExtraCard(player.name)

            if (!response) {
                if (player.handCards.show().size == 2) {
                    outputView.showPlayerCardsInfo(player)
                }
                break
            }
            player.drawCard(deck)
            outputView.showPlayerCardsInfo(player)
        }
        outputView.newLine()
    }

    private fun runDealerDrawPhase(
        dealer: Dealer,
        deck: Deck,
    ) {
        while (dealer.isDrawable()) {
            dealer.drawCard(deck)
            outputView.showDealerDrawMessage()
        }
        outputView.newLine()
    }

    private fun outputFinalResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showCardsResult(listOf(dealer) + players)
        outputView.newLine()

        val finalResult: Map<GameResult, Int> = GameResultRecord(dealer, players).getDealerResult()
        outputView.showFinalResult(finalResult, dealer, players)
    }
}
