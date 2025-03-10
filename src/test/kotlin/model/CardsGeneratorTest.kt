package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsGeneratorTest {
    private val cards = CardsGenerator().generateCards()

    @Test
    fun `게임에 사용될 52장의 카드를 생성한다`() {
        val cardsLength = cards.totalCount()
        assertThat(cardsLength).isEqualTo(52)
    }

    @ParameterizedTest
    @MethodSource("makeTestCardNames")
    fun `카드 목록에 생성된 카드 이름이 있는지 검증한다`(cardName: Pair<String, String>) {
        val result = cards.names().contains(cardName)
        Assertions.assertTrue(result)
    }

    companion object {
        @JvmStatic
        private fun makeTestCardNames(): Stream<Pair<String, String>> {
            return CardRank.entries.flatMap { card ->
                Shape.entries.map { shape ->
                    card.name to shape.name
                }
            }.stream()
        }
    }
}
