# [정리]

### 람다 파라미터에 함수를 전달할 수 있는 방법은 3가지이다.

```kotlin
1.temp(block = { func() })
2.temp(block = Temp::func)
3.temp { func() }
```

1. introduce { } 는 `람다를 괄호 밖으로 빼내는 관례`로 인해서 실제 코드는

``` kotlin
introduce({ })

fun introduce(a: () -> Unit) {  }

```

이지만, a는 마지막 파라미터이기에 소괄호를 뺀 상태이다.

2. 각각의 name, company, skills, languages는 함수이기에 함수로 구현한다.

```kotlin
fun name() {}
fun company() {}
fun skills() {}
fun languages() {}
```

3. 힌트로 제공된 introduce 함수는 `PersonBuilder` 객체 혹은 함수만을 람다로 받고, `person` 객체를 반환한다.

```kotlin
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
```

4. 힌트로 제공된 PersonBuilder 는 다음의 행동을 가진다.

- name 함수를 통해서 PersonBuilder 내부의 name 상태를 업데이트 한다.
- company 함수를 통해서 PersonBuilder 내부의 company 상태를 업데이트 한다.
- build 함수를 통해서 person 객체를 생성 후 반환한다.(이 때, 내부 상태의 name과 company로 생성한다.)

```kotlin
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

data class Person(val name: String, val company: String?)
```

5. SkillsBuilder와 LanguagesBuilder 또한 제공된 PersonBuilder와 같이 만든다.

```kotlin
// SkillsBuilder
class SkillsBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills = Skills(soft, hard)
}

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)
```

```kotlin
// LanguagesBuilder
class LanguagesBuilder {
    private val languages: MutableList<Pair<String, Int>> = mutableListOf()

    fun build(): Languages {
        val languages: List<LanguageModel> =
            languages.map { language -> LanguageModel(language.first, language.second) }
        return Languages(languages)
    }

    infix fun String.level(other: Int) {
        languages.add(this to other)
    }
}

data class Languages(
    val languages: List<LanguageModel>,
) {
    data class LanguageModel(
        val language: String,
        val level: Int,
    )
}
```

- 여기서 [`infix fun`](https://kotlinlang.org/docs/functions.html#infix-notation)은 중위 표기 함수이다.
    - 조건)
        - 멤버 함수이거나, 확장 함수여야 한다.
        - 하나의 파라미터만 가져야 한다.
        - 파라미터는 가변인자가 되어서는 안 되며, 기본값이 없어야 한다.
  ```kotlin
    infix fun Int.shl(x: Int): Int { ... }
  
    // calling the function using the infix notation
    1 shl 2

    // is the same as
    1.shl(2)
  ```

6. 하지만, introduce 함수는 결국 Person 객체를 반환하기에, 각각의 빌더를 따로 만들게 되면 테스트가 실패한다.

```kotlin
    fun skills(block: SkillsBuilder.() -> Unit): Skills = SkillsBuilder().apply(block).build()

fun languages(block: LanguagesBuilder.() -> Unit): Languages = LanguagesBuilder().apply(block).build()
```

```kotlin
// 우리가 성공해야 하는 테스트
@Test
fun introduceTest() {
    val person =
        introduce {
            name("박재성")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
    assertThat(person.languages.languages).isEqualTo(
        listOf(
            LanguageModel("Korean", 5),
            LanguageModel("English", 3),
        ),
    )
}
```

7. Person 객체에서 skills와 languages를 가질 수 있도록 수정하면, 위 각각의 빌더를 PersonBuilder 내부로 옮겨 내부에서 각각 skills 와 languages를 생성하도록 한다.

```kotlin
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}
```

기존 빌더 코드에서 반환 타입을 없앤 대신, PersonBuilder 의 상태로 선언하여 값을 할당해주는 식으로 변경한다.