package blackjack.model

import model.Name
import model.Names
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NamesTest {
    @Test
    fun `Names를 생성할 수 있다`() {
        val names = Names(listOf(Name("jason"), Name("pobi")))
        assertThat(names.size).isEqualTo(2)
        assertThat(names[0].value).isEqualTo("jason")
        assertThat(names[1].value).isEqualTo("pobi")
    }

    @Test
    fun `이름 개수가 8개를 넘으면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Names(
                listOf(
                    Name("jason"),
                    Name("pobi"),
                    Name("dooly"),
                    Name("sunny"),
                    Name("leah"),
                    Name("james"),
                    Name("mendel"),
                    Name("skot"),
                    Name("ropi")
                )
            )
        }
    }

    @Test
    fun `플레이어의 이름은 서로 중복되면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Names(
                listOf(
                    Name("pobi"),
                    Name("pobi"),
                )
            )
        }
    }
}
