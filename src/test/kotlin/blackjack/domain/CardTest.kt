package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "1, A스페이드", "2, 2스페이드", "3, 3스페이드", "4, 4스페이드", "5, 5스페이드", "6, 6스페이드",
        "7, 7스페이드", "8, 8스페이드", "9, 9스페이드", "10, 10스페이드", "11, J스페이드", "12, Q스페이드", "13, K스페이드",
        "14, A다이아몬드", "15, 2다이아몬드", "16, 3다이아몬드", "17, 4다이아몬드", "18, 5다이아몬드", "19, 6다이아몬드",
        "20, 7다이아몬드", "21, 8다이아몬드", "22, 9다이아몬드", "23, 10다이아몬드", "24, J다이아몬드", "25, Q다이아몬드", "26, K다이아몬드",
        "27, A하트", "28, 2하트", "29, 3하트", "30, 4하트", "31, 5하트", "32, 6하트",
        "33, 7하트", "34, 8하트", "35, 9하트", "36, 10하트", "37, J하트", "38, Q하트", "39, K하트",
        "40, A클로버", "41, 2클로버", "42, 3클로버", "43, 4클로버", "44, 5클로버", "45, 6클로버",
        "46, 7클로버", "47, 8클로버", "48, 9클로버", "49, 10클로버", "50, J클로버", "51, Q클로버", "52, K클로버"
    )
    fun `카드는 각 모양별로 2부터 10, A, J, Q, K가 존재한다`(number: Int, expected: String) {
        assertThat(Card.of(number).toString()).isEqualTo(expected)
    }
}
