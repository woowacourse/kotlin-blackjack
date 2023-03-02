package model

class PlayerCardDistributeConditionChecker : CardDistributeConditionChecker {
    override fun isDistributable(sumOfCards: Int): Boolean = sumOfCards < GameRule.WINNING_NUMBER
}
