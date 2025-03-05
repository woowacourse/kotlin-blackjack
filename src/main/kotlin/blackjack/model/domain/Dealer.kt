package blackjack.model.domain

class Dealer(val name: String = "딜러") : Participants {
    override val cards: MutableList<Card> = mutableListOf()
}
