package blackjack.controller

import blackjack.model.Betting
import blackjack.model.card.CardDeck
import blackjack.model.card.RandomDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.Dealer
import blackjack.model.playing.participants.Participants
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        val participants = prepareForGame()
        val cardDeck = RandomDeck()
        dealInitialCards(participants, cardDeck)
        playing(participants, cardDeck)
        showFinalWinning(participants)
    }

    private fun prepareForGame(): Participants {
        val dealer = Dealer(CardHand())
        val players = initPlayers()

        players.players.forEach {
            it.betting =
                Betting(
                    inputView.readBettingAmount(it),
                )
        }
        return Participants(dealer, players)
    }

    private fun dealInitialCards(
        participants: Participants,
        cardDeck: CardDeck,
    ) {
        outputView.printInitialSetting(participants)

        participants.addInitialCards(cardDeck)
        outputView.printInitialCardHands(participants)
    }

    private fun playing(
        participants: Participants,
        cardDeck: CardDeck,
    ) {
        runPlayersPhase(participants.players, cardDeck)
        runDealerPhase(participants.dealer, cardDeck)
    }

    private fun showFinalWinning(participants: Participants) {
        val finalWinning = participants.getFinalWinning()

        outputView.printGameResult(participants)
        outputView.printFinalWinning(finalWinning)
        outputView.printProfit(participants, finalWinning.playerWinning)
    }

    private fun initPlayers(): Players =
        Players(
            inputView.readPlayersName()
                .map { name ->
                    Player(PlayerName(name), CardHand())
                },
        )

    private fun runPlayersPhase(
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.players.forEach { player ->
            runPlayerPhase(player, cardDeck)
        }
    }

    private fun runPlayerPhase(
        player: Player,
        cardDeck: CardDeck,
    ) {
        while (player.canDraw() && askDraw(player)) {
            val newCard = cardDeck.draw()
            player.draw(newCard)
            outputView.printPlayerCardHand(player)
        }
    }

    private fun askDraw(player: Player) = inputView.readIsHit(player)

    private fun runDealerPhase(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        if (dealer.canDraw()) {
            outputView.printDealerHit()
            val newCard = cardDeck.draw()
            dealer.draw(newCard)
        }
    }
}
