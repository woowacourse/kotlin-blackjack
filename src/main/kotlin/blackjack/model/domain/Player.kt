package blackjack.model.domain

class Player(override val name: String) : Participants {
    override val cards: MutableList<Card> = mutableListOf()
    override var status: Status = Status.None

    fun isAlive(number: Int) {
        if (status != Status.Bust) {
            status = Status.compare(sumCardNumber, number)
        }
    }
}
