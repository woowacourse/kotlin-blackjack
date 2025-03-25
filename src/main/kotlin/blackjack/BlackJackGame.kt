package blackjack

import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.GameViewListener
import blackjack.view.OutputView

class BlackJackGame(
    private val gameViewListener: GameViewListener,
    private val outputView: OutputView,
) {
    fun play(participants: Participants) {
        val deck = getDeck()

        dealInitialCards(participants, deck)

        outputView.showInitialCards(participants.dealer, participants.players)

        handlePlayersHit(participants.players, deck)
        handleDealerHit(participants.dealer, deck)

        showResult(participants.dealer, participants.players)
    }

    private fun getDeck(): Deck {
        val shuffledCards: List<Card> = Card.getAllCard().shuffled()
        return Deck(shuffledCards)
    }

    private fun dealInitialCards(
        participants: Participants,
        deck: Deck,
    ) {
        participants.list.forEach { participant ->
            repeat(INITIAL_CARD_COUNT) {
                participant.addCard(deck.draw())
            }
        }
    }

    private fun handleDealerHit(
        dealer: Dealer,
        deck: Deck,
    ) {
        if (dealer.canHit()) {
            outputView.printDealerHaveAdditionalCard()

            while (dealer.canHit()) {
                dealer.addCard(deck.draw())
            }
        }
    }

    private fun handlePlayersHit(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            handlePlayerHit(player, deck)
        }
    }

    private fun handlePlayerHit(
        player: Player,
        deck: Deck,
    ) {
        while (player.canHit()) {
            val result = gameViewListener.onPlayerHit(player.name)
            if (result) {
                player.addCard(deck.draw())
                outputView.printPlayerCards(player)
            } else {
                break
            }
        }
    }

    private fun showResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val gameResult = GameResult(dealer, players)

        outputView.printFinalCards(dealer, players)
        outputView.printResult(gameResult)
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
