package blackjack.domain

import blackjack.domain.SingleValueRank.FaceRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharacterTest {
    @Test
    fun `캐릭터 카드는 Jack, Queen, King이 존재한다`() {
        assertThat(FaceRank.entries).hasSameElementsAs(setOf(FaceRank.JACK, FaceRank.QUEEN, FaceRank.KING))
    }

    @Test
    fun `캐릭터 카드 Jack은 숫자 10으로 계산한다`() {
        val faceCard = FaceRank.JACK
        assertThat(faceCard.value).isEqualTo(10)
    }

    @Test
    fun `캐릭터 카드 Queen은 숫자 10으로 계산한다`() {
        val faceCard = FaceRank.QUEEN
        assertThat(faceCard.value).isEqualTo(10)
    }

    @Test
    fun `캐릭터 카드 King은 숫자 10으로 계산한다`() {
        val faceCard = FaceRank.KING
        assertThat(faceCard.value).isEqualTo(10)
    }
}
