package domain

import domain.deck.Deck
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.cards.Cards
import domain.judge.Referee
import domain.judge.Result

class BlackjackGame(private val names: List<String>) {
    val dealer = Dealer(Cards(listOf()))
    val players: List<Player> = names.map { Player(it, Cards(listOf())) }

    fun startGame() {
        Deck.makeDeck()
        dealer.makeStartDeck()
        players.forEach {
            it.makeStartDeck()
        }
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

    fun getPlayerWinningResult() = Referee(dealer, players).judgePlayersResult()

    fun judgeDealerResult(playersResult: Map<String, Result>): List<Result> =
        playersResult.map { it.value.reverseResult() }
}
