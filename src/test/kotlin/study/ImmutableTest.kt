package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Car(val car: String, val distance: Int = 0)

class ImmutableTest {
    @Test
    fun mutableTest() {
        val mutableList = mutableListOf<Car>()
        mutableList.add(Car("오둥"))
        assertThat(mutableList).contains(Car("오둥"))
    }

    @Test
    fun immutableList() {
        val list: List<Car> = listOf()
        val newList = list.plus(Car("오둥"))
        assertThat(newList).contains(Car("오둥"))
    }

    @Test
    fun mutableCars() {
        val input = listOf("알", "송")
        val cars = mutableListOf<Car>()
        input.forEach {
            cars.add(Car(it))
        }
        assertThat(cars).containsAll(listOf(Car("알"), Car("송")))
    }

    @Test
    fun getWinner() {
        val cars =
            listOf(
                Car("a", 10),
                Car("b", 20),
                Car("c", 30),
            )
        val winnerCar = cars.maxBy { it.distance }
        assertThat(winnerCar).isEqualTo(Car("c", 30))
    }

    @Test
    fun getWinnerByReduce() {
        val cars =
            listOf(
                Car("a", 10),
                Car("b", 20),
                Car("c", 30),
            )
        val winnerCar =
            cars.reduce { acc, car ->
                if (acc.distance > car.distance) acc else car
            }
    }
}
