package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharacterTest {
    @Test
    fun `캐릭터 카드는 Jack, Queen, King이 존재한다`() {
        assertThat(Face.entries).hasSameElementsAs(setOf(Face.JACK, Face.QUEEN, Face.KING))
    }

    @Test
    fun `캐릭터 카드 Jack은 숫자 10으로 계산한다`() {
        val faceCard = Face.JACK
        assertThat(faceCard.possibleValues).hasSameElementsAs(setOf(10))
    }

    @Test
    fun `캐릭터 카드 Queen은 숫자 10으로 계산한다`() {
        val faceCard = Face.QUEEN
        assertThat(faceCard.possibleValues).hasSameElementsAs(setOf(10))
    }

    @Test
    fun `캐릭터 카드 King은 숫자 10으로 계산한다`() {
        val faceCard = Face.KING
        assertThat(faceCard.possibleValues).hasSameElementsAs(setOf(10))
    }
}
