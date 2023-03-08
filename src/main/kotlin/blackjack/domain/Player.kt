package blackjack.domain

class Player(name: String) : Participant(name) {
    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun canDraw(): Boolean = true // getCards().calculateTotalScore() <= blackjackScore()
}
