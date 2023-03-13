package domain

import domain.card.Card
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
        deck.makeRandomDeck(Card.getAllCard().shuffled())
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

    fun getPlayersWinningResult(players: Players): Map<String, Result> = players.getPlayersWinningResult(dealer)

    fun judgeDealerResult(playersResult: Map<String, Result>): List<Result> =
        playersResult.map { it.value.reverseResult() }

    fun getPlayerRewards(players: Players, playersResult: Map<String, PlayerResultInfo>): Map<String, Int> =
        players.getPlayersReward(playersResult)

    fun calculateDealerRewards(playerResults: List<Int>): Int = playerResults.sum() * -1
}
