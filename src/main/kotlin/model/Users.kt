package model

import entity.Dealer
import entity.Players

class Users(val players: Players, val dealer: Dealer = Dealer()) {
    fun distribute(cardFactory: CardFactory) {
        dealer.cards.addCards(cardFactory.generate(BlackjackStage.INITIAL_CARD_DISTRIBUTE_COUNT))
        players.value.forEach {
            it.cards.addCards(cardFactory.generate(BlackjackStage.INITIAL_CARD_DISTRIBUTE_COUNT))
        }
    }
}
