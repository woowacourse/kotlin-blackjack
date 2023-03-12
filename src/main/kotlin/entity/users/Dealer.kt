package entity.users

class Dealer(gameInformation: GameInformation) : User(gameInformation) {
    override fun isDistributable(): Boolean = cardsNumberSum() <= MAXIMUM_CARD_SUM_NUMBER

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 16
    }
}
