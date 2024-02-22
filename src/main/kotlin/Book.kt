// Define a data class for Book with properties id, title, author, and status
data class Book(
    val id: Int,         // ID of the book
    val title: String,   // Title of the book
    val author: String,  // Author of the book
    var status: String   // Status of the book
) {
    // Method to print the details of the book
    fun bookDetails() {
        println("ID: $id")
        println("Title: $title")
        println("Author: $author")
        println("Status: $status")
    }
}
