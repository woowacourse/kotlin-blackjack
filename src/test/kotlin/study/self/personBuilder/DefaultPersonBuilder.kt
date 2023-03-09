package study.self.personBuilder

import domain.card.Card
import domain.card.HandOfCards
import domain.person.Dealer
import domain.person.Player

class DefaultPersonBuilder : PersonBuilder {
    private var name: String? = null
    private var handOfCards: HandOfCards? = null

    override fun name(name: String) {
        this.name = name
    }

    override fun addTwoCards(card1: Card, card2: Card) {
        this.handOfCards = HandOfCards(card1, card2)
    }

    override fun buildDealer(): Dealer {
        return Dealer(
            handOfCards ?: throw NullPointerException("딜러 생성과정에서 null값이 들어왔습니다."),
        )
    }

    override fun buildPlayer(): Player {
        return Player(
            name ?: throw NullPointerException("플레이어 생성과정에서 null값이 들어왔습니다."),
            handOfCards ?: throw NullPointerException("플레이어 생성과정에서 null값이 들어왔습니다."),
        )
    }
}
