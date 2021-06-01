package domain

class Gamers(inputNames: String) {
    val names: List<User> = inputNames.split(",").map { Gamer(it) }

}
