package domain

import domain.card.Card
import domain.card.CardMaker
import domain.deck.Deck
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.cards.Cards
import domain.judge.Referee
import domain.judge.Result

class BlackjackGame(private val names: List<String>) {
    private val deck: Deck = Deck(CardMaker().makeCards())
    lateinit var dealer: Dealer
    val players = mutableListOf<Player>()

    init {
        makeParticipants()
    }

    private fun makeParticipants() {
        makeDealer()
        makePlayer(names)
    }

    private fun makeDealer() {
        dealer = Dealer(Cards(makeStartDeck()))
    }

    private fun makePlayer(names: List<String>) {
        names.forEach { name ->
            val startDeck = makeStartDeck()
            players.add(Player(name, Cards(startDeck)))
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
        players.find { it.name == name }!!.pickCard(deck.giveCard())
    }

    fun pickDealerCard() {
        dealer.pickCard(deck.giveCard())
    }

    fun checkBurst(name: String) = players.find { it.name == name }!!.checkBurst()

    fun checkDealerAvailableForPick(): Boolean {
        return dealer.checkAvailableForPick()
    }

    fun getPlayerWinningResult() = Referee(dealer, players).judgePlayersResult()

    fun judgeDealerResult(playersResult: Map<String, Result>) = mutableListOf<Result>().apply {
        playersResult.forEach {
            add(it.value.reverseResult())
        }
    }
}
