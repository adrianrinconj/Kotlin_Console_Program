import java.util.*

/**
 * Main function to start the library application.
 */
fun main() {
    println("Welcome to Rincon Library!\n")
    val scanner = Scanner(System.`in`)
    var option: Int
    do {
        printMenu()
        try {
            option = scanner.nextInt()
            when (option) {
                1 -> showBooks()
                2 -> idSearch(scanner)
                3 -> returnBook(scanner)
                4 -> checkOut(scanner)
                5 -> return
                else -> println("Sorry, that number is not an option. Please select a number from the list.")
            }
        } catch (e: InputMismatchException) {
            println("Invalid input. Please enter a valid number.")
            scanner.next() // Clear the invalid input from the scanner
            option = -1 // Set option to an invalid value to avoid an infinite loop
        }
    } while (option != 5)
}

/**
 * Prints the main menu of the library application.
 */
fun printMenu() {
    println("Please select from the following options...")
    println("1. Show book list")
    println("2. Search for book by ID number")
    println("3. Return book")
    println("4. Check out book")
    println("5. Exit\n")
}

/**
 * Function to display the list of books.
 */
fun showBooks() {
    bookList.books.forEach {
        it.bookDetails()
        println()
    }
}

/**
 * Function to search for a book by its ID number.
 * @param scanner The Scanner object used for user input.
 */
fun idSearch(scanner: Scanner) {
    while (true) {
        try {
            var validInput = false
            print("Please enter the ID of the book you would like to look up (type the number 0 to return to menu):")
            val inputtedId = scanner.nextInt()
            if (inputtedId == 0) return
            for (book in bookList.books) {
                if (inputtedId == book.id) {
                    book.bookDetails()
                    validInput = true
                    break
                }
            }
            if (!validInput) println("No books have that ID number. Please try again (ID should be a number from 1 to 5).")
            break // Exit the loop after successful input
        } catch (e: InputMismatchException) {
            println("Invalid input. Please enter a valid number.")
            scanner.next() // Clear the invalid input from the scanner
        }
    }
}

/**
 * Function to check out a book.
 * @param scanner The Scanner object used for user input.
 */
fun checkOut(scanner: Scanner) {
    while (true) {
        try {
            print("Please enter the ID of the book you would like to check-out (type 0 to return to the menu): ")
            val getBook = scanner.nextInt()
            if (getBook == 0) return
            for (book in bookList.books) {
                if (book.id == getBook && book.status != "Checked-out") {
                    book.status = "Checked-out"
                    println("You have successfully checked-out!")
                    return
                } else if (book.id == getBook && book.status == "Checked-out") {
                    println("Sorry, that book has already been checked out!")
                    return
                }
            }
            println("Sorry, no books have that number ID. Please try again.")
            break // Exit the loop after successful input
        } catch (e: InputMismatchException) {
            println("Invalid input. Please enter a valid number.")
            scanner.next() // Clear the invalid input from the scanner
        }
    }
}

/**
 * Function to return a book.
 * @param scanner The Scanner object used for user input.
 */
fun returnBook(scanner: Scanner) {
    while (true) {
        try {
            print("Please enter the number ID of the book you would like to return (type 0 to return to the menu): ")
            val giveBook = scanner.nextInt()
            if (giveBook == 0) return
            for (book in bookList.books) {
                if (book.id == giveBook && book.status != "Available") {
                    book.status = "Available"
                    println("You have returned our book!")
                    return
                } else if (book.id == giveBook && book.status == "Available") {
                    println("That book has already been returned")
                    return
                }
            }
            println("Sorry, that number is not a valid book ID. Please try again (input a number from 1 to 5)")
            break // Exit the loop after successful input
        } catch (e: InputMismatchException) {
            println("Invalid input. Please enter a valid number.")
            scanner.next() // Clear the invalid input from the scanner
        }
    }
}

/**
 * Object representing the list of books in the library.
 */
object bookList {
    val books = arrayListOf(
        Book(1, "The Brief and Wondrous Life of Oscar Wao", "Junot Diaz", "Available"),
        Book(2, "Roots", "Alex Haley", "Available"),
        Book(3, "I Have No Mouth, and I Must Scream", "Harlan Ellison", "Available"),
        Book(4, "To Kill a Mockingbird", "Harper Lee", "Available"),
        Book(5, "The Great Gatsby", "F. Scott Fitzgerald", "Available")
    )
}
