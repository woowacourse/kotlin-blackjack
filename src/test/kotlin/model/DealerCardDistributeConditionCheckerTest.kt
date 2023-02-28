package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DealerCardDistributeConditionCheckerTest {
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16])
    @ParameterizedTest
    fun `딜러가 가진 카드의 숫자 합이 16 이하이면 한장의 카드를 더 받을 수 있다`(sumOfCardNumber: Int) {
        val dealerCardDistributeConditionChecker = DealerCardDistributeConditionChecker()
        val isDistributable = dealerCardDistributeConditionChecker.isDistributable(sumOfCardNumber)

        assertThat(isDistributable).isTrue
    }

    @ValueSource(ints = [17, 18, 19, 20, 21])
    @ParameterizedTest
    fun `딜러가 가진 카드의 숫자 합이 16 초과이면 한장의 카드를 더 받을 수 없다`(sumOfCardNumber: Int) {
        val dealerCardDistributeConditionChecker = DealerCardDistributeConditionChecker()
        val isDistributable = dealerCardDistributeConditionChecker.isDistributable(sumOfCardNumber)

        assertThat(isDistributable).isFalse
    }
}
