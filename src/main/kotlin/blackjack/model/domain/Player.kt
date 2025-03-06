package blackjack.model.domain

class Player(override val name: String) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    override var alive: Boolean = true

    fun isAlive(number: Int) {
        if (sumCardNumber < number) {
            alive = false
        }
    }
}
