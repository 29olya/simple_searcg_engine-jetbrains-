package search
import java.io.File

fun main(args: Array<String>) {

    val inputList = File(args.last()).readLines()
    val mapForSearch: MutableMap<String, List<Int>> = mutableMapOf()

    fun invertedIndex(input: List<String>): Map<String, List<Int>> {
        val distinctList = mutableListOf<String>()
        for (string in input) {
            for (word in string.split(" "))
                distinctList.add(word.lowercase())
        }
        distinctList.distinct()
        for (item in distinctList) {
            val countList = mutableListOf<Int>()
            for (i in input.indices) {
                if (input[i].contains(item, ignoreCase = true)) {
                    countList.add(i)
                }
            }
            mapForSearch[item] = countList
        }
        return mapForSearch
    }
    fun search(){
        println("Select a matching strategy: ALL, ANY, NONE")
        val matchingStrategy = readln()
        val searchQuery = readln().lowercase()
        when (matchingStrategy) {
            "ALL" -> {
                for (string in inputList) {
                    if (string.contains(searchQuery,ignoreCase = true)) {
                        println(string)
                    }
                }
                if (!inputList.contains(searchQuery)) {
                    println("No matches found")
                }
            }

            "ANY" -> {
                val anyList = mutableListOf<Int>()
                for (word in searchQuery.split(" ")) {
                    if (mapForSearch.containsKey(word)) {
                        for (item in mapForSearch.getValue(word)) {
                            anyList.add(item)
                        }
                    }
                }
                anyList.distinct()
                for (item in anyList) {
                    println(inputList[item])
                }
                if (anyList.isEmpty()) {
                    println("No matches found")
                }
            }

            "NONE" -> {
                val anyList = mutableListOf<Int>()
                for (word in searchQuery.split(" ")) {
                    if (mapForSearch.containsKey(word)) {
                        for (item in mapForSearch.getValue(word)) {
                            anyList.add(item)
                        }
                    }
                }
                anyList.distinct()
                val noneList: MutableList<Int> = (inputList.indices).toMutableList()
                noneList.removeAll(anyList)
                for (item in noneList) {
                    println(inputList[item])
                }
                if (noneList.isEmpty()) {
                    println("No matches found")
                }
            }
        }

    }
    fun printAll() {
        for (element in inputList) {
            println(element)
        }
    }
    fun exit() {
        println("Bye!")
    }
    val menu = """
       === Menu ===
       1. Find a person
       2. Print all people
       0. Exit
   """.trimIndent()
    println(menu)
    invertedIndex(inputList)
    var choice = readln().toInt()
    while (choice != 0) {
        choice = when (choice) {
            1 -> {
                search()
                readln().toInt()
            }

            2 -> {
                printAll()
                readln().toInt()
            }

            else -> {
                println("Incorrect option! Try again.")
                readln().toInt()
            }
        }
    }
    if (choice == 0) {
        exit()
    }
}