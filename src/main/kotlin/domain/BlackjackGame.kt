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
    private val dealer: Dealer
    private val players: List<Player>

    init {
        dealer = Dealer(Cards(makeStartDeck()))
        players = makePlayer(names)
    }

    private fun makeStartDeck(): MutableList<Card> {
        val startDeck = mutableListOf<Card>()
        repeat(START_NUMBER_OF_CARDS) {
            startDeck.add(deck.giveCard())
        }
        return startDeck
    }

    private fun makePlayer(names: Names): List<Player> {
        return names.userNames.map {
            Player(it, Cards(makeStartDeck()))
        }
    }

    fun startBlackjackGame(startGame: (List<Player>, Dealer) -> Unit) {
        startGame(players, dealer)
    }

    fun playsTurn(wantPickCard: (Player) -> Boolean, onPickCard: (Player) -> Unit) {
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
        while (!player.checkBurst()) {
            val answer = wantPickCard()
            if (answer) player.ownCards.pickCard(deck.giveCard()) else return
            onPickCard()
        }
    }

    fun dealerPickCard(onPickCard: () -> Unit) {
        while (pickDealerCardIfPossible())
            onPickCard()
    }

    private fun pickDealerCardIfPossible(): Boolean {
        if (!dealer.checkOverCondition()) {
            dealer.ownCards.pickCard(deck.giveCard())
            return true
        }
        return false
    }

    fun printCardResult(showResult: (Dealer, List<Player>) -> Unit) {
        showResult(dealer, players)
    }

    fun printWinningResult(showWinningResult: (List<Result>, List<ParticipantResult>) -> Unit) {
        val playerResult = getPlayerWinningResult()
        val dealerResult = judgeDealerResult(playerResult)
        showWinningResult(dealerResult, playerResult)
    }

    private fun getPlayerWinningResult(): List<ParticipantResult> = dealer.judgePlayersResult(players)

    private fun judgeDealerResult(playersResult: List<ParticipantResult>): List<Result> {
        return playersResult.map {
            it.result.reverseResult()
        }
    }

    companion object {
        private const val START_NUMBER_OF_CARDS = 2
    }
}
