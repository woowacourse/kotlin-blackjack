package domain

import domain.deck.Deck
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.Players
import domain.gamer.cards.Cards
import domain.judge.Referee
import domain.judge.Result

class BlackjackGame() {
    val dealer = Dealer(Cards(listOf()))
    var players = Players(listOf())
        private set

    fun startGame(names: List<String>) {
        initPlayers(names)
        Deck.makeDeck()
        dealer.makeStartDeck()
        players.getPlayers().forEach {
            it.makeStartDeck()
        }
    }

    private fun initPlayers(names: List<String>) {
        players = Players(names.map { Player(it, Cards(listOf())) })
    }

    fun pickPlayerCard(player: Player) {
        player.pickCard(Deck.giveCard())
    }

    fun pickDealerCard() {
        dealer.pickCard(Deck.giveCard())
    }

    fun checkBurst(player: Player) = player.checkBurst()

    fun checkDealerAvailableForPick(): Boolean {
        return dealer.checkAvailableForPick()
    }

    fun getPlayerWinningResult() = Referee(dealer, players.getPlayers()).judgePlayersResult()

    fun judgeDealerResult(playersResult: Map<String, Result>): List<Result> =
        playersResult.map { it.value.reverseResult() }
}
