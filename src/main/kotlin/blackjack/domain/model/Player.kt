package blackjack.domain.model

class Player(name: String) : Participant(name) {
    constructor(name: String, cards: List<Card>) : this(name) {
        accept(cards)
    }
}
