package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

enum class Character(
    override val possibleValues: List<Int>,
) : Rank {
    JACK(listOf(10)),
    QUEEN(listOf(10)),
    KING(listOf(10)),
}

class CharacterTest {
    @Test
    fun `캐릭터 카드는 Jack, Queen, King이 존재한다`() {
        assertThat(Character.entries).isEqualTo(listOf(Character.JACK, Character.QUEEN, Character.KING))
    }

    @Test
    fun `캐릭터 카드 Jack은 숫자 10으로 계산한다`() {
        val characterCard = Character.JACK
        assertThat(characterCard.possibleValues).contains(10)
    }

    @Test
    fun `캐릭터 카드 Queen은 숫자 10으로 계산한다`() {
        val characterCard = Character.QUEEN
        assertThat(characterCard.possibleValues).contains(10)
    }

    @Test
    fun `캐릭터 카드 King은 숫자 10으로 계산한다`() {
        val characterCard = Character.KING
        assertThat(characterCard.possibleValues).contains(10)
    }
}
