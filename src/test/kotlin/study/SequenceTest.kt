package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.time.measureTime

class SequenceTest {
    @Test
    fun sequence() {
        val sequence = sequenceOf(1, 2, 3, 4, 5)
        val result =
            sequence
                .map { it * 2 }
                .filter { it > 5 }
        assertThat(result).isEqualTo(listOf(6, 8, 10))
    }

    @Test
    fun `List 와 비교`() {
        val listMeasuredTime =
            measureTime {
                val result =
                    listOf(1, 2, 3, 4, 5)
                        .map { it * 2 }
                        .filter { it > 5 }
                println(result.size)
            }

        val sequenceMeasuredTime =
            measureTime {
                val result =
                    sequenceOf(1, 2, 3, 4, 5)
                        .map { it * 2 }
                        .filter { it > 5 }
                        .toList()
                println(result.size)
            }
        println(listMeasuredTime.inWholeMicroseconds)
        println(sequenceMeasuredTime.inWholeMicroseconds)
        assertThat(listMeasuredTime > sequenceMeasuredTime).isTrue
    }

    @Test
    fun `List 와 비교2`() {
        val listMeasuredTime =
            measureTime {
                val result =
                    LargeList
                        .sortedDescending()
                println(result.size)
            }

        val sequenceMeasuredTime =
            measureTime {
                val result =
                    LargeList
                        .asSequence()
                        .sortedDescending()
                        .toList()
                println(result.size)
            }
        println(listMeasuredTime.inWholeMicroseconds)
        println(sequenceMeasuredTime.inWholeMicroseconds)
        assertThat(listMeasuredTime > sequenceMeasuredTime).isTrue
    }

    companion object {
        private val LargeList = List(1_000_000) { it }
    }
}
