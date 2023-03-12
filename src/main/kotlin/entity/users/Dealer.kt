package entity.users

class Dealer(userInformation: UserInformation) : User(userInformation) {
    override fun isDistributable(): Boolean = cardsNumberSum() <= MAXIMUM_CARD_SUM_NUMBER

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 16
    }
}
