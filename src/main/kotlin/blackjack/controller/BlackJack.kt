package blackjack.controller

import blackjack.model.card.CardDeck
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
    private val cardDeck: CardDeck,
) {
    fun gameStart() {
        val dealer = Dealer(CardHand())
        val players = initPlayers()

        val participants = Participants(dealer, players)

        dealInitialCards(participants)
        runPlayersPhase(players)

        runDealerPhase(dealer)
        showFinalWinning(participants)
    }

    private fun initPlayers(): Players =
        Players(
            inputView.readPlayersName()
                .map { name ->
                    Player(PlayerName(name), CardHand())
                },
        )

    private fun dealInitialCards(participants: Participants) {
        outputView.printInitialSetting(participants)

        participants.addInitialCards(cardDeck)
        outputView.printInitialCardHands(participants)
    }

    private fun runPlayersPhase(players: Players) {
        players.players.forEach { player ->
            runPlayerPhase(player)
        }
    }

    private fun runPlayerPhase(player: Player) {
        while (player.canDraw() && askDraw(player)) {
            player.draw(cardDeck)
            outputView.printPlayerCardHand(player)
        }
    }

    private fun askDraw(player: Player) = inputView.readIsHit(player)

    private fun runDealerPhase(dealer: Dealer) {
        if (dealer.canDraw()) {
            outputView.printDealerHit()
            dealer.draw(cardDeck)
        }
    }

    private fun showFinalWinning(participants: Participants) {
        val finalWinning = participants.getFinalWinning()

        outputView.printGameResult(participants)
        outputView.printFinalWinning(finalWinning)
    }
}
