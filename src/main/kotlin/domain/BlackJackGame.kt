package domain

import domain.card.Deck
import domain.participant.*
import view.inputHit
import view.printStatus

class BlackJackGame(val players: Players = Players(), val dealer: Dealer = Dealer(), val deck: Deck = Deck()) {
    val participants: Participants = Participants(listOf(dealer) + players)

    fun initStage() {
        repeat(2) { participants.drawCard(deck) }
    }

    fun getProfitResult() : List<Pair<String, Int>> {
        val result = getGameResult(dealer, players.players)
        val dealerResult = Pair(dealer.name, -result.sumBy { it.second })
        return listOf(dealerResult) + result
    }
}



fun getGameResult(dealer: Dealer, players: List<Player>): List<Pair<String, Int>> {
    return players.map { Pair(it.name, it.calculateProfit(getPlayerResult(dealer, it))) }
}

private fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
    return getResultType(player.hand - dealer.hand, player)
}
