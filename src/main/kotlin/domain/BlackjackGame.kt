package domain

import domain.card.Card
import domain.card.CardMaker
import domain.deck.Deck
import domain.gamer.cards.DealerCards
import domain.gamer.cards.PlayerCards
import domain.judge.ParticipantResult
import domain.judge.Referee
import domain.judge.Result
import domain.player.Names
import domain.player.Player

class BlackjackGame(
    names: Names,
    private val deck: Deck = Deck(CardMaker().makeShuffledCards())
) {
    val dealerState: DealerCards
    val players: List<Player>

    init {
        dealerState = DealerCards(makeStartDeck())
        players = makePlayer(names)
    }

    private fun makePlayer(names: Names): List<Player> {
        return names.userNames.map {
            Player(it, PlayerCards(makeStartDeck()))
        }
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(START_NUMBER_OF_CARDS) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }

    fun pickPlayerCard(name: String) {
        findPlayer(name).ownCards.pickCard(deck.giveCard())
    }

    fun findPlayer(name: String): Player {
        return players.first { it.name == name }
    }

    fun pickDealerCard() {
        dealerState.pickCard(deck.giveCard())
    }

    fun checkBurst(name: String) = findPlayer(name).ownCards.checkOverCondition()

    fun checkDealerAvailableForPick(): Boolean {
        return !dealerState.checkOverCondition()
    }

    fun getPlayerWinningResult(): List<ParticipantResult> = Referee(dealerState, players).judgePlayersResult()

    fun judgeDealerResult(playersResult: List<ParticipantResult>): List<Result> {
        return playersResult.map {
            it.result.reverseResult()
        }
    }

    companion object {
        private const val START_NUMBER_OF_CARDS = 2
    }
}
