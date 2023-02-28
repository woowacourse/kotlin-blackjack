package model

class PlayerCardDistributeConditionChecker : CardDistributeConditionChecker {
    override fun isDistributable(sumOfCards: Int): Boolean = sumOfCards < MAXIMUM_CARD_SUM_NUMBER

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 21
    }
}
