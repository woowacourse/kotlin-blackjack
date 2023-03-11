package domain

import domain.card.Card
import domain.card.CardMaker
import domain.card.Cards
import domain.deck.Deck
import domain.participants.Dealer
import domain.participants.Names
import domain.participants.Player

class BlackjackGame(
    names: Names,
    bettingMoney: List<Int>,
    private val deck: Deck = Deck(CardMaker().makeShuffledCards())
) {
    val dealer: Dealer
    val players: List<Player>

    init {
        dealer = Dealer(Cards(makeStartDeck()))
        players = makePlayer(names, bettingMoney)
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(START_NUMBER_OF_CARDS) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }

    private fun makePlayer(names: Names, money: List<Int>): List<Player> {
        return names.userNames.mapIndexed { index, name ->
            Player(name, Cards(makeStartDeck()), money[index])
        }
    }

    fun playsTurn(
        wantPickCard: (Player) -> Boolean,
        onPickCard: (Player) -> Unit,
        onDealerPickCard: () -> Unit
    ) {
        playsPlayerTurn(wantPickCard, onPickCard)

        while (pickDealerCardIfPossible())
            onDealerPickCard()
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

    fun showCardResult(showResult: (Dealer, List<Player>) -> Unit) {
        showResult(dealer, players)
    }

    fun showWinningResult(showWinningResult: (List<Player>) -> Unit) {
        judgePlayersResult(players)
        showWinningResult(players)
    }

    private fun judgePlayersResult(players: List<Player>) {
        players.map {
            it.setResult(dealer.judgePlayerResult(it.ownCards))
        }
    }

    companion object {
        private const val START_NUMBER_OF_CARDS = 2
    }
}
