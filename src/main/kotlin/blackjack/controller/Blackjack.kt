package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.RandomShuffler
import blackjack.domain.Score.Companion.SCORE_DEALER_HIT_UNTIL
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.view.AskView
import blackjack.view.ResultView
import blackjack.view.model.PlayerSummary

class Blackjack(
    private val askView: AskView = AskView(),
    private val resultView: ResultView = ResultView(),
) {
    fun play() {
        val deck = Deck(RandomShuffler)
        val players: List<Player> = askView.readPlayers().toPlayers(deck)
        val dealer = Dealer(deck)
        startDealing(players, dealer)
        startPlayersTurn(players)
        startDealerTurn(dealer)
        showResult(dealer, players)
    }

    private fun startDealing(
        players: List<Player>,
        dealer: Dealer,
    ) {
        resultView.showDealing(
            playersName = players.names,
            dealerCards = dealer.cardsPrettyString,
            playersCards = players.cardsPrettyStrings,
        )
    }

    private fun startPlayersTurn(players: List<Player>) {
        players.forEach { player ->
            showPlayerCard(player)
            hitOrStay(player)
        }
    }

    private fun showPlayerCard(player: Player) {
        resultView.showPlayerCard(
            PlayerSummary(
                player.name,
                player.cards.prettyString,
                player.score.value,
            ),
        )
    }

    private fun hitOrStay(player: Player) {
        while (!player.isFinished) {
            val wantToHit: Boolean = askView.askWantToHit(player.name)
            if (wantToHit) {
                player.hit()
                showPlayerCard(player)
            } else {
                player.stay()
            }
        }
    }

    private fun startDealerTurn(dealer: Dealer) {
        dealer.hit()
        hitOrStay(dealer)
    }

    private fun hitOrStay(dealer: Dealer) {
        while (!dealer.isFinished) {
            if (dealer.score < SCORE_DEALER_HIT_UNTIL) {
                resultView.showDealerHit()
                dealer.hit()
            } else {
                dealer.stay()
            }
        }
    }

    private fun showResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        resultView.showParticipantsSummary(
            dealer.summary,
            players.summaries,
        )
        resultView.showProfit(players.toResults(dealer.state))
    }
}
