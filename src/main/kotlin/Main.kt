import java.util.*

/*
Взглянем на ТЗ.
- Кофемашина умеет готовить четыре вида кофе:
эспрессо, американо, капучино и латте.
- Требуется создать класс CoffeeMachine с публичным
методом start() и полями water, milk и beans целочисленного типа.
- При вызове метод start() должен выводить сообщение
"Кофемашина готова к работе", а после в бесконечном цикле
выводить сообщение "Введите команду" и обрабатывать ввод.
Рассмотрим каждую команду по-отдельности:
- "выключить" — по этой команде мы должны вывести сообщение
"До свидания!" и выйти из функции.
- "наполнить" — увеличиваем значение переменных до максимальных
(water до 2000, milk до 1000 и beans до 500) и выводим сообщение
"Ингридиенты пополнены".
- "статус" — выводим сообщение "Ингридиентов осталось:",
 а после - список оставшихся ингредиентов в следующем
 формате: "$water мл воды\n$milk мл молока\n$beans гр кофе".
- "кофе" — выводим "Введите название напитка или "назад" для
возврата в главное меню" и обрабатываем введённую команду.
Командой может быть как название кофе, так и фраза "назад",
после которой мы должны вернуться к предыдущему состоянию.
Если введено неизвестное название кофе, вывести фразу "Рецепт не найден!".
Если введено верное название кофе, то мы должны уменьшить
запасы ингредиентов согласно рецепту и вывести сообщение "$coffeeName готов".
Если у нас недостаточно ингредиентов, вывести сообщение
"Недостаточно воды!" или "Недостаточно молока!" или
"Недостаточно кофе!" и вернуться к предыдущему состоянию.
Рецепты кофе:
- Эспрессо — 60 мл воды и 10 гр кофейных зёрен.
- Американо — 120 мл воды и 10 гр кофейных зёрен.
- Капучино — 120 мл воды, 20 гр кофейных зёрен и 60 мл молока.
- Латте — 240 мл воды, 20 гр кофейных зёрен и 120 мл молока.

Рецепты кофе удобно оформить в виде enum или data-класса
Прочитать ввод из терминала вы можете с помощью функции
 nextLine() класса Scanner
Команда может прийти в любом регистре, поэтому после
считывания текста из терминала приводите его к нижнему
 регистру используя метод lowercase() класса String.
 */


fun main(args: Array<String>) {

    val coffeemachine = CoffeeMachine()
    coffeemachine.start()
}
/*
Взглянем на ТЗ.
- Кофемашина умеет готовить четыре вида кофе: эспрессо, американо, капучино и латте.
- Требуется создать класс CoffeeMachine с публичным методом start() и полями water, milk и beans целочисленного типа.
- При вызове метод start() должен выводить сообщение "Кофемашина готова к работе", а после в бесконечном цикле
выводить сообщение "Введите команду" и обрабатывать ввод.
 */

class CoffeeMachine {

    private var scanner = Scanner(System.`in`)
    private var water: Int = 0
    private var milk: Int = 0
    private var beans: Int = 0


    fun start() {
        println("Кофемашина готова к работе")

        while (true) {     // цикл обработки команд

            println("Введите команду")
            var lineFromConsole = scanner.nextLine().lowercase()

            when (lineFromConsole) {
                cmd_turnOff -> {
                    println("До свидания!"); break
                }

                cmd_fillIngrt -> fillIngdnts()
                cmd_status -> statusIngdnts()
                cmd_makeCoffee -> setCoffee()
            }
        }
    }

    /*
    Рассмотрим каждую команду по-отдельности:
- "выключить" — по этой команде мы должны вывести сообщение "До свидания!" и выйти из функции.
- "наполнить" — увеличиваем значение переменных до максимальных (water до 2000, milk до 1000 и beans до 500) и выводим сообщение
"Ингридиенты пополнены".
- "статус" — выводим сообщение "Ингридиентов осталось:", а после - список оставшихся ингредиентов в следующем
 формате: "$water мл воды\n$milk мл молока\n$beans гр кофе".
 Рецепты кофе:
- Эспрессо — 60 мл воды и 10 гр кофейных зёрен.
- Американо — 120 мл воды и 10 гр кофейных зёрен.
- Капучино — 120 мл воды, 20 гр кофейных зёрен и 60 мл молока.
- Латте — 240 мл воды, 20 гр кофейных зёрен и 120 мл молока.
     */

    companion object {
        private const val cmd_turnOff = "выключить"
        private const val cmd_fillIngrt = "наполнить"
        private const val cmd_status = "статус"
        private const val cmd_makeCoffee = "кофе"
        private const val cmd_back = "назад"
    }

    /*
    Рецепты кофе:
- Эспрессо — 60 мл воды и 10 гр кофейных зёрен.
- Американо — 120 мл воды и 10 гр кофейных зёрен.
- Капучино — 120 мл воды, 20 гр кофейных зёрен и 60 мл молока.
- Латте — 240 мл воды, 20 гр кофейных зёрен и 120 мл молока.
     */
    enum class CoffeeTask(val nameCoffee: String, val waterDose: Int, val beansDose: Int, val milkDose: Int) {
        ESPRESSO("эспрессо", 60, 10, 0),
        AMERICANO("американо", 120, 10, 0),
        CAPUCHINO("капучино", 120, 20, 60),
        LATTE("латте", 240, 20, 120),

    }

    /*
    - "наполнить" — увеличиваем значение переменных до максимальных (water до 2000, milk до 1000 и beans до 500) и выводим сообщение
"Ингридиенты пополнены".
     */
    private fun fillIngdnts() {                // (water до 2000, milk до 1000 и beans до 500)
        if (water < 2000) water = 2000
        if (milk < 1000) milk = 1000
        if (beans < 500) beans = 500
        println("Ингридиенты пополнены.")
    }

    /*
- "статус" — выводим сообщение "Ингридиентов осталось:", а после - список оставшихся ингредиентов в следующем
формате: "$water мл воды\n$milk мл молока\n$beans гр кофе".
 */
    private fun statusIngdnts() {
        println("Ингридиентов осталось:")
        println("$water мл воды\n$milk мл молока\n$beans гр кофе")
    }


    /*
    - "кофе" — выводим "Введите название напитка или "назад" для возврата в главное меню" и обрабатываем введённую команду.
Командой может быть как название кофе, так и фраза "назад", после которой мы должны вернуться к предыдущему состоянию.
Если введено неизвестное название кофе, вывести фразу "Рецепт не найден!".
Если введено верное название кофе, то мы должны уменьшить запасы ингредиентов согласно рецепту и вывести сообщение "$coffeeName готов".
Если у нас недостаточно ингредиентов, вывести сообщение "Недостаточно воды!" или "Недостаточно молока!" или
"Недостаточно кофе!" и вернуться к предыдущему состоянию.
     */

    private fun setCoffee() {
        println("Введите название напитка или \"назад\" для возврата в главное меню")
        var console = scanner.nextLine().lowercase()
        when (console) {
            cmd_back -> return;
            CoffeeTask.ESPRESSO.nameCoffee -> makeCoffee(CoffeeTask.ESPRESSO.nameCoffee)
            CoffeeTask.AMERICANO.nameCoffee -> makeCoffee(CoffeeTask.AMERICANO.nameCoffee)
            CoffeeTask.CAPUCHINO.nameCoffee -> makeCoffee(CoffeeTask.CAPUCHINO.nameCoffee)
            CoffeeTask.LATTE.nameCoffee -> makeCoffee(CoffeeTask.LATTE.nameCoffee)
            else -> println("Рецепт не найден!")
        }
    }


    private fun makeCoffee(coffee: String) {

        if (checkQuantity(coffee, water, milk, beans)) {
            water -= CoffeeTask.ESPRESSO.waterDose
            beans -= CoffeeTask.ESPRESSO.beansDose
            println("${CoffeeTask.ESPRESSO.nameCoffee} готов")
        }
        if (checkQuantity(coffee, water, milk, beans)) {
            water -= CoffeeTask.AMERICANO.waterDose
            beans -= CoffeeTask.AMERICANO.beansDose
            println("${CoffeeTask.AMERICANO.nameCoffee} готов")
        }
        if (checkQuantity(coffee, water, milk, beans)) {
            water -= CoffeeTask.CAPUCHINO.waterDose
            beans -= CoffeeTask.CAPUCHINO.beansDose
            milk -= CoffeeTask.CAPUCHINO.milkDose
            println("${CoffeeTask.CAPUCHINO.nameCoffee} готов")
        }
        if (checkQuantity(coffee, water, milk, beans)) {
            water -= CoffeeTask.LATTE.waterDose
            beans -= CoffeeTask.LATTE.beansDose
            milk -= CoffeeTask.LATTE.milkDose
            println("${CoffeeTask.LATTE.nameCoffee} готов")
        }

    }

    /*
    enum class coffeeTask(val nameCoffee: String, val waterDose: Int, val beansDose: Int, val milkDose: Int) {
    ESPRESSO("эспрессо", 60, 10, 0),
    AMERICANO("американо", 120, 10, 0),
    CAPUCHINO("капучино", 120, 20, 60),
    LATTE("латте", 240, 20, 120);
 */

   private fun checkQuantity(coffee: String, water: Int, milk: Int, beans: Int): Boolean {

       return when(coffee){
           "эспрессо" -> water > CoffeeTask.ESPRESSO.waterDose || beans > CoffeeTask.ESPRESSO.beansDose
           "американо"-> water > CoffeeTask.AMERICANO.waterDose || beans > CoffeeTask.AMERICANO.beansDose
           "капучино" -> water > CoffeeTask.CAPUCHINO.waterDose || milk > CoffeeTask.CAPUCHINO.milkDose || beans > CoffeeTask.CAPUCHINO.beansDose
           "латте" -> water > CoffeeTask.LATTE.waterDose || milk > CoffeeTask.LATTE.milkDose || beans > CoffeeTask.LATTE.beansDose
           else -> return false
       }

   }

}





