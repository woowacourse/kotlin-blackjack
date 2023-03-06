package domain.card

import domain.constant.BlackJackConstants

class Cards {
    private val _value = mutableListOf<Card>()
    val value: List<Card>
        get() = _value.toList()

    // BUST 가 아니면 ACE 하나를 11로 보고 합계 계산
    fun getTotalCardNumber(): Int {
        val sumExceptAce: Int = calculateSumExceptAce()
        val aceCount: Int = countAce()
        return sumExceptAce + calculateSumAce(BlackJackConstants.BLACK_JACK - sumExceptAce, aceCount)
    }

    // ACE 를 무조건 1로 보고 합계 계산
    fun getMinTotalCardNumber(): Int = calculateSumExceptAce() + countAce()

    fun add(card: Card) {
        _value.add(card)
    }

    private fun countAce() = _value.count { it.number == CardNumber.ACE }

    private fun calculateSumExceptAce() = _value
        .filter { it.number != CardNumber.ACE }
        .sumOf { it.number.value }

    private fun calculateSumAce(availableMax: Int, aceCount: Int) = when {
        aceCount == BlackJackConstants.NOTHING -> BlackJackConstants.NOTHING
        availableMax >= BlackJackConstants.BIG_ACE + aceCount - 1 -> BlackJackConstants.BIG_ACE + (aceCount - 1) * BlackJackConstants.SMALL_ACE
        else -> aceCount * BlackJackConstants.SMALL_ACE
    }
}
