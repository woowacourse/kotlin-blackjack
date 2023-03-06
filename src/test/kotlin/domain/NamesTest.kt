package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NamesTest {
    fun Names(vararg name: String): Names {
        return Names(name.map(::Name))
    }

    @Test
    fun `이름은 최대 8개까지 허용된다`() {
        assertThrows<IllegalStateException> {
            Names(
                "aaaa",
                "bbbb",
                "cccc",
                "dddd",
                "eeee",
                "ffff",
                "ggggg",
                "hhhh",
                "i",
                "j",
                "k"
            )
        }
    }
}
