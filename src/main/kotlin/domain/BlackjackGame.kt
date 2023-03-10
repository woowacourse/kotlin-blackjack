package domain

import domain.card.Card
import domain.deck.Deck
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.Players
import domain.gamer.cards.Cards
import domain.judge.Result

class BlackjackGame(
    private val deck: Deck,
    val dealer: Dealer = Dealer(Cards(emptyList())),
    private var _players: Players = Players(emptyList())
) {
    val players: Players get() = _players
    fun startGame(names: List<String>) {
        initPlayers(names)
        deck.makeRandomDeck(Card.getAllCard().shuffled())
        dealer.makeStartDeck(deck)
        players.getPlayers().forEach {
            it.makeStartDeck(deck)
        }
    }

    private fun initPlayers(names: List<String>) {
        _players = Players(names.map { Player(it, Cards(listOf())) })
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

    fun getPlayerWinningResult() = mutableMapOf<String, Result>().apply {
        players.getPlayers().forEach {
            this[it.name] = it.judgeResult(dealer.cards)
        }
    }

    fun judgeDealerResult(playersResult: Map<String, Result>): List<Result> =
        playersResult.map { it.value.reverseResult() }
}
