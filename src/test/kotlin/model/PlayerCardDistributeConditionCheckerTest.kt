package model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerCardDistributeConditionCheckerTest {
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20])
    @ParameterizedTest
    fun `플레이어가 가진 카드의 숫자 합이 21 미만이면 한장의 카드를 더 받을 수 있다`(sumOfCardNumber: Int) {
        val playerCardDistributeConditionChecker = PlayerCardDistributeConditionChecker()
        val isDistributable = playerCardDistributeConditionChecker.isDistributable(sumOfCardNumber)

        Assertions.assertThat(isDistributable).isTrue
    }

    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 이상이면 한장의 카드를 더 받을 수 없다`() {
        val playerCardDistributeConditionChecker = PlayerCardDistributeConditionChecker()
        val isDistributable = playerCardDistributeConditionChecker.isDistributable(21)

        Assertions.assertThat(isDistributable).isFalse
    }
}
