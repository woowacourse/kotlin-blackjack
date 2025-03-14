package blackjack.controller

import blackjack.model.betting.BettingManager
import blackjack.model.card.CardDeck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.DrawManager
import blackjack.model.participant.Money
import blackjack.model.participant.ParticipantManager
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.WinningManager
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val participantManager: ParticipantManager = ParticipantManager()
    private val drawManager: DrawManager = DrawManager()
    private val bettingManager: BettingManager = BettingManager()
    private val winningManager: WinningManager = WinningManager()

    fun run() {
        val cardDeck = CardDeck()
        val participants =
            participantManager.prepareParticipants(
                dealerName = DEFAULT_DEALER_NAME,
                distributeCards = cardDeck::draw,
                getPlayerNames = inputView::getPlayers,
            )
        val (dealer, players) = participants.dealer to participants.players

        progressBetting(players)

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        progressPlayersDraw(players, cardDeck)
        progressDealerDraw(dealer, cardDeck)

        endGame(participants)
    }

    private fun progressBetting(players: Players) {
        outputView.displayInitialMoney()
        bettingManager.getPlayersMoney(players) { name ->
            Money(inputView.getBettingMoney(name.toString()))
        }
    }

    private fun progressPlayersDraw(
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
        }
        players.value.forEach { player ->
            drawManager.progressPlayerDraw(
                player = player,
                cards = cardDeck::draw,
                choice = { inputView.getIsRecieveMore(player.name.toString()) },
                onCardReceived = { cards -> outputView.displayParticipantCards(player.name, cards) },
            )
        }
    }

    private fun progressDealerDraw(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        drawManager.progressDealerDraw(dealer, cardDeck::draw)

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount)
        outputView.displayParticipantInfo(dealer.name, dealer.cards, dealer.score)
    }

    private fun endGame(participants: Participants) {
        participants.players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score)
        }

        val winningResult = winningManager.result(participants)
        val bettingResult = bettingManager.result(winningResult, participants)

        outputView.displayProfitTitle()
        bettingResult.value.forEach { (name, money) ->
            outputView.displayProfit(name.toString(), money.value)
        }
    }
}
