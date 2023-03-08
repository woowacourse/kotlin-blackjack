package domain

import domain.card.Card
import domain.card.CardMaker
import domain.card.Cards
import domain.deck.Deck
import domain.judge.ParticipantResult
import domain.judge.Result
import domain.participants.Dealer
import domain.participants.Names
import domain.participants.Player

class BlackjackGame(
    names: Names,
    private val deck: Deck = Deck(CardMaker().makeShuffledCards())
) {
    val dealer: Dealer
    val players: List<Player>

    init {
        dealer = Dealer(Cards(makeStartDeck()))
        players = makePlayer(names)
    }

    private fun makePlayer(names: Names): List<Player> {
        return names.userNames.map {
            Player(it, Cards(makeStartDeck()))
        }
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(START_NUMBER_OF_CARDS) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }

    private fun pickPlayerCard(name: String) {
        findPlayer(name).ownCards.pickCard(deck.giveCard())
    }

    private fun findPlayer(name: String): Player {
        return players.first { it.name == name }
    }

    private fun pickDealerCard() {
        dealer.ownCards.pickCard(deck.giveCard())
    }

    fun repeatPickCard(
        name: String,
        validatePickAnswer: () -> Boolean,
        printCards: (String, List<Card>) -> Unit
    ) {
        while (!checkBurst(name)) {
            val answer = validatePickAnswer()
            if (answer) pickPlayerCard(name) else return
            printCards(name, findPlayer(name).ownCards.cards)
        }
    }

    private fun checkBurst(name: String) = findPlayer(name).checkBurst()

    fun pickDealerCardIfPossible(): Boolean {
        if (!dealer.checkOverCondition()) {
            pickDealerCard()
            return true
        }
        return false
    }

    fun getPlayerWinningResult(): List<ParticipantResult> = dealer.judgePlayersResult(players)

    fun judgeDealerResult(playersResult: List<ParticipantResult>): List<Result> {
        return playersResult.map {
            it.result.reverseResult()
        }
    }

    companion object {
        private const val START_NUMBER_OF_CARDS = 2
    }
}
