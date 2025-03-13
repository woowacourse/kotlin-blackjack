package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.BettingManager
import blackjack.model.game.DrawManager
import blackjack.model.game.ParticipantManager
import blackjack.model.game.WinningManager
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.Money
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val participantManager = ParticipantManager()
        val drawManager = DrawManager()
        val bettingManager = BettingManager()
        val cardDeck = CardDeck()

        val participants =
            participantManager.prepareParticipants(
                dealerName = DEFAULT_DEALER_NAME,
                cardDeck = cardDeck,
                getPlayerNames = inputView::getPlayers,
            )
        val (dealer, players) = participants.dealer to participants.players

        progressBetting(bettingManager, players)

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        progressPlayersDraw(drawManager, players, cardDeck)
        progressDealerDraw(drawManager, dealer, cardDeck)

        endGame(participants, bettingManager)
    }

    private fun progressBetting(
        bettingManager: BettingManager,
        players: Players,
    ) {
        outputView.displayInitialMoney()
        bettingManager.getPlayersMoney(players) { name ->
            Money(inputView.getBettingMoney(name.toString()))
        }
    }

    private fun progressPlayersDraw(
        drawManager: DrawManager,
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
        }
        players.value.forEach { player ->
            drawManager.progressPlayerDraw(
                player = player,
                draw = cardDeck::draw,
                getCommand = { inputView.getIsRecieveMore(player.name.toString()) },
                onCardReceived = { cards -> outputView.displayParticipantCards(player.name, cards) },
            )
        }
    }

    private fun progressDealerDraw(
        drawManager: DrawManager,
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        drawManager.progressDealerDraw(dealer, cardDeck::draw)

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount())
        outputView.displayParticipantInfo(dealer.name, dealer.cards, dealer.score())
    }

    private fun endGame(
        participants: Participants,
        bettingManager: BettingManager,
    ) {
        participants.players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score())
        }

        val winningManager = WinningManager(participants)
        val bettingResult = bettingManager.result(participants, winningManager.result())

        outputView.displayProfitTitle()
        bettingResult.value.forEach { (name, money) ->
            outputView.displayProfit(name.toString(), money.value)
        }
    }
}
