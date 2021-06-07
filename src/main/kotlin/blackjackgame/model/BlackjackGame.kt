package blackjackgame.model

import blackjackgame.model.card.Card
import blackjackgame.model.card.Deck
import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Participant
import blackjackgame.model.player.Player
import blackjackgame.model.player.Players
import blackjackgame.model.result.Result
import blackjackgame.model.result.getPlayerResult

class BlackjackGame(players: List<Participant>, dealer: Participant, private val deck: Deck) {
    private val participants: Players

    init {
        val playerGroup = mutableListOf(dealer)
        players.forEach { playerGroup.add(it) }
        participants = Players(playerGroup)
    }

    fun start() {
        participants.drawInitCards(deck)
    }

    fun getInitStatus(): List<Pair<String, List<Card>>> {
        return participants.map { Pair(it.name, it.getInitCards()) }
    }

    fun isExistHitPlayer(): Boolean {
        return participants.asSequence()
            .filter { it.isPlayer() }
            .any { it.canDraw() && it.isPlaying }
    }

    fun playTurn(answer: Boolean): Pair<String, List<Card>> {
        val player = findTurnPlayer()
        if (!answer) {
            player.endTurn()
            return Pair(player.name, player.cards.getCards())
        }
        player.drawCard(deck.handOutCard())
        return Pair(player.name, player.cards.getCards())
    }

    fun findTurnPlayer(): Participant {
        return participants.asSequence()
            .filter { it.isPlayer() }
            .first { it.isHit() && it.isPlaying }
    }

    fun extractResult(): List<Triple<String, List<Card>, Int>> {
        return participants.map { Triple(it.name, it.cards.getCards(), it.cards.calculateFinalScore()) }
    }

    fun playDealerTurn(): Boolean {
        val dealer = participants.first { !it.isPlayer() } as Dealer
        if (dealer.isAvailableToDraw()) {
            dealer.drawCard(deck.handOutCard())
            return true
        }
        return false
    }

    fun extractWinLoseResult(): List<Pair<String, Result>> {
        val dealer: Dealer = participants.first { !it.isPlayer() } as Dealer
        val players = participants
            .filter { it.isPlayer() }
            .map { it as Player }

        val playerResult = players.map { Pair(it.name, getPlayerResult(dealer, it)) }
        val dealerResult = Pair(dealer.name, playerResult.reverse())

        val finalResult = mutableListOf(dealerResult)
        playerResult.forEach { finalResult.add(it) }

        return finalResult
    }

    private fun List<Pair<String, Result>>.reverse(): Result {
        val results: List<Result> = this.map { it.second }
        return results.reduce { a, b -> a + b }.reverse()
    }

    fun extractMoneyResult(): List<Pair<String, Int>> {
        val dealer: Dealer = participants.first { !it.isPlayer() } as Dealer
        val players = participants.filter { it.isPlayer() }

        val dealerMoney: Int = players
            .map { it.initialMoney - it.finalMoney }
            .reduce { a, b -> a + b }.toInt()
        dealer.earnMoney(dealerMoney)
        return getMoneyResult(dealer, players)
    }

    private fun getMoneyResult(dealer: Dealer, players: List<Participant>): List<Pair<String, Int>> {
        val results = mutableListOf<Pair<String, Int>>()
        results.add(dealer.name to dealer.finalMoney)
        players.map {
            results.add(it.name to it.finalMoney)
        }
        return results.toList()
    }
}
