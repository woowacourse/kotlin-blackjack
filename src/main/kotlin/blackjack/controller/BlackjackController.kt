package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.BettingManager
import blackjack.model.game.ParticipantManager
import blackjack.model.game.WinningManager
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.Money
import blackjack.model.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val participantManager = ParticipantManager()
        val bettingManager = BettingManager()
        val cardDeck = CardDeck()
        val dealer = participantManager.prepareDealer(DEFAULT_DEALER_NAME, cardDeck)
        val players = preparePlayers(participantManager, cardDeck)
        val winningManager = WinningManager(dealer, players)

        outputView.displayInitialMoney()
        bettingManager.getPlayersMoney(players) { name ->
            Money(inputView.getBettingMoney(name.toString()))
        }

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        progressPlayersDraw(participantManager, players, cardDeck)
        progressDealerDraw(participantManager, dealer, cardDeck)

        displayParticipantsInfo(players)

        val profitResult = bettingManager.end(dealer, winningManager.generateResult())

        outputView.displayProfitTitle()
        profitResult.bettingResult.forEach { (name, money) ->
            outputView.displayProfit(name.toString(), money.value)
        }
    }

    private fun preparePlayers(
        participantManager: ParticipantManager,
        cardDeck: CardDeck,
    ): Players {
        val playerNames = inputView.getPlayers()
        val players = participantManager.preparePlayers(playerNames, cardDeck)

        return players
    }

    private fun progressPlayersDraw(
        participantManager: ParticipantManager,
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
        }
        players.value.forEach { player ->
            participantManager.progressPlayerDrawUntilFinished(
                player = player,
                draw = cardDeck::draw,
                getCommand = { inputView.getIsRecieveMore(player.name.toString()) },
                onCardReceived = { cards -> outputView.displayParticipantCards(player.name, cards) },
            )
        }
    }

    private fun progressDealerDraw(
        participantManager: ParticipantManager,
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        participantManager.progressDealerDraw(dealer, cardDeck::draw)

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount())
        outputView.displayParticipantInfo(dealer.name, dealer.cards, dealer.score())
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score())
        }
    }
}
