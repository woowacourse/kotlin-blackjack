package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

enum class Character {
    JACK,
    QUEEN,
    KING, ;

    val value = 10
}

class CharacterTest {
    // 캐릭터 카드 `Jack`, `Queen`, `King`은 10으로 계산된다.
    @Test
    fun `캐릭터 카드는 Jack, Queen, King이 존재한다`() {
        assertThat(Character.entries).isEqualTo(listOf(Character.JACK, Character.QUEEN, Character.KING))
    }

    @Test
    fun `캐릭터 카드 Jack은 숫자 10으로 계산한다`() {
        val characterCard = Character.JACK
        assertThat(characterCard.value).isEqualTo(10)
    }

    @Test
    fun `캐릭터 카드 Queen은 숫자 10으로 계산한다`() {
        val characterCard = Character.QUEEN
        assertThat(characterCard.value).isEqualTo(10)
    }

    @Test
    fun `캐릭터 카드 King은 숫자 10으로 계산한다`() {
        val characterCard = Character.KING
        assertThat(characterCard.value).isEqualTo(10)
    }
}
