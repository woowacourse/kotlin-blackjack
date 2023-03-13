package domain

import domain.card.Card
import domain.card.CardMaker
import domain.card.Cards
import domain.deck.Deck
import domain.participants.Dealer
import domain.participants.Money
import domain.participants.Names
import domain.participants.Player
import domain.result.ParticipantsResult

class BlackjackGame(
    names: Names,
    getBetAmount: (Names) -> List<Money>,
    private val deck: Deck = Deck(CardMaker().makeShuffledCards())
) {
    val dealer: Dealer
    val players: List<Player>

    init {
        dealer = Dealer(Cards(makeStartDeck()))
        players = makePlayer(names) { getBetAmount(names) }
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(START_NUMBER_OF_CARDS) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }

    private fun makePlayer(names: Names, getMoney: (Names) -> List<Money>): List<Player> {
        val money = getMoney(names)
        return names.userNames.mapIndexed { index, name ->
            Player(name, Cards(makeStartDeck()), money[index])
        }
    }

    fun play(
        wantPickCard: (Player) -> Boolean,
        onPickCard: (Player) -> Unit,
        onDealerPickCard: () -> Unit
    ): Pair<ParticipantsResult, Int> {
        playsPlayerTurn(wantPickCard, onPickCard)

        while (pickDealerCardIfPossible())
            onDealerPickCard()
        val result = BlackjackResult(dealer, players).getResult()
        return Pair(result, result.playerResult.sumOf { it.profit * REVERSE })
    }

    private fun playsPlayerTurn(
        wantPickCard: (Player) -> Boolean,
        onPickCard: (Player) -> Unit
    ) {
        players.forEach { player ->
            repeatPickCard(
                player,
                { wantPickCard(player) },
                { onPickCard(player) }
            )
        }
    }

    private fun repeatPickCard(
        player: Player,
        wantPickCard: () -> Boolean,
        onPickCard: () -> Unit
    ) {
        while (!player.ownCards.checkBurst()) {
            val answer = wantPickCard()
            if (answer) player.ownCards.pickCard(deck.giveCard()) else return
            onPickCard()
        }
    }

    private fun pickDealerCardIfPossible(): Boolean {
        if (!dealer.checkOverCondition()) {
            dealer.ownCards.pickCard(deck.giveCard())
            return true
        }
        return false
    }

    companion object {
        private const val START_NUMBER_OF_CARDS = 2
        private const val REVERSE = -1
    }
}
