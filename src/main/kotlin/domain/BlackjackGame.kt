package domain

import domain.deck.Deck
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.Players
import domain.gamer.cards.Cards
import domain.judge.PlayerResultInfo
import domain.judge.Result

class BlackjackGame(
    private val deck: Deck,
    val dealer: Dealer = Dealer(Cards(emptyList()))
) {
    fun startGame(players: Players) {
        dealer.makeStartDeck(deck)
        players.makeStartDecks(deck)
    }

    fun pickPlayerCard(player: Player) {
        player.pickCard(deck.giveCard())
    }

    fun pickDealerCard() {
        dealer.pickCard(deck.giveCard())
    }

    fun checkBurst(player: Player) = player.cards.isBurst()

    fun checkDealerAvailableForPick(): Boolean {
        return dealer.isAvailableForPick()
    }

    fun getPlayersWinningResult(players: Players): Map<Player, Result> = players.getPlayersWinningResult(dealer)

    fun judgeDealerResult(playersResult: Map<Player, Result>): List<Result> =
        playersResult.map { it.value.reverseResult() }

    fun getPlayerRewards(players: Players, playersResult: Map<Player, PlayerResultInfo>): Map<String, Int> =
        players.getPlayersReward(playersResult)

    fun calculateDealerRewards(playerResults: List<Int>): Int = playerResults.sum() * -1
}
