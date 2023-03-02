package model

import entity.Dealer
import entity.Players

class BlackjackStage(val cardDistributor: CardDistributor) {
    fun distributeAllUsers(dealer: Dealer, players: Players) {
        dealer.cards.addCards(cardDistributor.distribute(INITIAL_CARD_DISTRIBUTE_COUNT))
        players.value.forEach {
            it.cards.addCards(cardDistributor.distribute(INITIAL_CARD_DISTRIBUTE_COUNT))
        }
    }

    companion object {
        const val INITIAL_CARD_DISTRIBUTE_COUNT = 2
    }
}
