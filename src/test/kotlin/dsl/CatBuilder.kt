package dsl

class CatBuilder {
    private var age: Int = 0
    private lateinit var gender: String

    fun age(value: Int) {
        age = value
    }

    fun gender(value: String) {
        gender = value
    }

    fun build(name: String): Cat {
        return Cat(name, age, gender)
    }
}

