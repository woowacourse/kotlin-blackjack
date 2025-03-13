package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.RandomShuffler
import blackjack.view.BlackjackView

class BlackjackController(
    val view: BlackjackView = BlackjackView(),
) {
    lateinit var players: List<Player>
    lateinit var dealer: Dealer
    lateinit var blackjack: Blackjack

    fun initGame() {
        val playerNames: List<String> = view.readPlayers()
        players = playerNames.toPlayers
        dealer = Dealer(players, RandomShuffler)
        blackjack = Blackjack(dealer)
    }

    fun dealCards() {
        blackjack.dealCards()
        view.dealCards(
            players.names,
            dealer.cards.prettyString,
            players.playersCards,
        )
    }

    fun playPlayerTurn() {
        blackjack.startPlayerTurn(
            onStart = { player ->
                view.showPlayerCard(
                    player.name,
                    player.cards.prettyString,
                    player.score.value,
                )
            },
            wantToHit = { player ->
                view.askWantToHit(player.name)
            },
            afterHit = { player ->
                view.showPlayerCard(
                    player.name,
                    player.cards.prettyString,
                    player.score.value,
                )
            },
        )
    }

    fun playDealerTurn() {
        blackjack.startDealerTurn {
            view.showDealerHit()
        }
    }

    fun showParticipantsSummary() {
        view.showParticipantsSummary(
            dealer.summary,
            players.summaries,
        )
    }

    fun setResult() {
        blackjack.setResult()
        view.showResult(
            dealer.result,
            players.results,
        )
    }
}
