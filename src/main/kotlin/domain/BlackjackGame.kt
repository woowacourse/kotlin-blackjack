package domain

import domain.card.Card
import domain.card.CardMaker
import domain.deck.Deck
import domain.gamer.state.DealerState
import domain.gamer.state.PlayerState

class BlackjackGame(private val names: List<String>) {
    private val deck: Deck = Deck(CardMaker().makeCards())
    lateinit var dealerState: DealerState
    val playersStates = mutableMapOf<String, PlayerState>()

    init {
        makeParticipants()
    }

    private fun makeParticipants() {
        makeDealer()
        makePlayer(names)
    }

    private fun makeDealer() {
        dealerState = DealerState(makeStartDeck())
    }

    private fun makePlayer(names: List<String>) {
        names.forEach {
            val startDeck = makeStartDeck()
            playersStates[it] = PlayerState(startDeck)
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
        playersStates.getValue(name).pickCard(deck.giveCard())
    }

    fun pickDealerCard() {
        dealerState.pickCard(deck.giveCard())
    }

    fun checkBurst(name: String) = playersStates.getValue(name).checkBurst()

    fun checkDealerAvailableForPick(): Boolean {
        return dealerState.checkAvailableForPick()
    }
}
