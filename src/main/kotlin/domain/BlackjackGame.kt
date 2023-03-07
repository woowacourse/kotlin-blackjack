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
    val players = Players(listOf())

    fun startGame(names: List<String>) {
        initPlayers(names)
        Deck.makeDeck()
        dealer.makeStartDeck()
        players.getPlayers().forEach {
            it.makeStartDeck()
        }
    }

    private fun initPlayers(names: List<String>) {
        players.addPlayers(names)
    }

    fun pickPlayerCard(player: Player) {
        player.pickCard(Deck.giveCard())
    }

    fun pickDealerCard() {
        dealer.pickCard(Deck.giveCard())
    }

    fun checkBurst(player: Player) = player.checkBurst()//players.find { it.name == name }!!.checkBurst()

    fun checkDealerAvailableForPick(): Boolean {
        return dealer.checkAvailableForPick()
    }

    fun getPlayerWinningResult() = Referee(dealer, players.getPlayers()).judgePlayersResult()

    fun judgeDealerResult(playersResult: Map<String, Result>): List<Result> =
        playersResult.map { it.value.reverseResult() }
}
