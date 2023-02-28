package model

interface CardDistributeConditionChecker {
    fun isDistributable(sumOfCards: Int): Boolean
}
