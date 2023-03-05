package domain

import domain.card.Card
import domain.card.CardMaker
import domain.deck.Deck
import domain.gamer.state.DealerState
import domain.gamer.state.PlayerState
import domain.judge.Referee
import domain.judge.Result

class BlackjackGame(private val names: List<String>) {
    private val deck: Deck = Deck(CardMaker().makeShuffledCards())
    val dealerState: DealerState
    val players = mutableListOf<Player>()

    init {
        dealerState = DealerState(makeStartDeck())
        makePlayer(names)
    }

    private fun makePlayer(names: List<String>) {
        names.forEach {
            val startDeck = makeStartDeck()
            players.add(Player(it, PlayerState(startDeck)))
        }
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(2) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }

    fun pickPlayerCard(name: String) {
        players.find { it.name == name }?.state?.pickCard(deck.giveCard())
    }

    fun pickDealerCard() {
        dealerState.pickCard(deck.giveCard())
    }

    fun checkBurst(name: String) = players.find { it.name == name }?.state?.checkBurst()

    fun checkDealerAvailableForPick(): Boolean {
        return dealerState.checkAvailableForPick()
    }

    fun getPlayerWinningResult() = Referee(dealerState, players).judgePlayersResult()

    fun judgeDealerResult(playersResult: Map<String, Result>) = mutableListOf<Result>().apply {
        playersResult.forEach {
            add(it.value.reverseResult())
        }
    }
}
