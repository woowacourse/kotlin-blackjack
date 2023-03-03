package domain

import domain.card.Card
import domain.card.CardMaker
import domain.deck.Deck
import domain.gamer.state.DealerState
import domain.gamer.state.PlayerState

class BlackjackGame(private val names: List<String>) {
    private val deck: Deck = Deck(CardMaker().makeCards())
    lateinit var dealerState: DealerState
    val playersStates = mutableListOf<PlayerState>()

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
        repeat(names.size) {
            val startDeck = makeStartDeck()
            playersStates.add(PlayerState(startDeck))
        }
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(2) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }
}
