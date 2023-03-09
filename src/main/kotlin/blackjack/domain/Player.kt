package blackjack.domain

class Player(name: String, private val needToDraw: () -> Boolean) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = !isBust() && needToDraw()
}