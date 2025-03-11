package blackjack.view

import blackjack.domain.Blackjack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.RandomShuffler

class BlackjackView(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun readPlayers(): List<Player> {
        outputView.requestPlayers()
        return inputView.readPlayers()
    }

    fun dealCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        outputView.showCardDealing(players, dealer)
    }

    fun startPlayerTurn(
        dealer: Dealer,
        blackjack: Blackjack,
    ) {
        println()
        blackjack.startPlayerTurn { player ->
            outputView.showPlayersCard(player)
            while (player.canHitMore()) {
                outputView.askWantToHit(player)
                val wantToHit = inputView.readWantToHit()
                if (!wantToHit) return@startPlayerTurn
                dealer.giveCard(player)
                outputView.showPlayersCard(player)
            }
        }
    }

    fun startDealerTurn(
        players: List<Player>,
        dealer: Dealer,
        blackjack: Blackjack,
    ) {
        blackjack.startDealerTurn {
            outputView.showDealerHit()
        }
        outputView.endDealerTurn(players, dealer)
    }

    fun showResult(
        players: List<Player>,
        dealer: Dealer,
        blackjack: Blackjack,
    ) {
        blackjack.setResult()
        outputView.showResult(players, dealer)
    }

    fun run() {
        val players = readPlayers()
        val dealer = Dealer(players, RandomShuffler)
        val blackjack = Blackjack(dealer)
        blackjack.dealCards()
        dealCards(players, dealer)
        startPlayerTurn(dealer, blackjack)
        startDealerTurn(players, dealer, blackjack)
        showResult(players, dealer, blackjack)
    }
}
