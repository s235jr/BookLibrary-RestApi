$(document).ready(function () {

    var body = $("body");

    $.ajax({
        url: "http://localhost:8080/books/",
        data: {},
        type: "GET",
        dataType: "json",
        success: function (json) {

            for (var i = 0; i < json.length; i++) {

                var newBook = $("<h3>");
                newBook.attr("id", json[i].id).attr("data-type", "GET");
                newBook.text(json[i].title);
                var newButton = $("<button>").text("Delete").attr("data-type", "DELETE");
                var newDiv = $("<div>");

                newBook.one("click", function showBook() {

                    $.ajax({
                        url: "http://localhost:8080/books/" + $(this).attr("id"),
                        data: {},
                        type: "GET",
                        dataType: "json",
                        success: function (json) {

                            var elementDiv = $("#" + json.id).next().next();

                            var isbn = $("<p>").text("ISBN: " + json.isbn);
                            var title = $("<p>").text("Title: " + json.title);
                            var author = $("<p>").text("Author: " + json.author);
                            var publisher = $("<p>").text("Publisher: " + json.publisher);
                            var type = $("<p>").text("Type: " + json.type);

                            elementDiv.append(isbn);
                            elementDiv.append(title);
                            elementDiv.append(author);
                            elementDiv.append(publisher);
                            elementDiv.append(type);
                        },
                        error: function (xhr, status,
                                         errorThrown) {
                        },
                        complete: function (xhr, status) {
                        }
                    });
                });

                newButton.on("click", function deleteBook() {
                    $.ajax({
                        url: "http://localhost:8080/books/" + $(this).prev().attr("id"),
                        data: {},
                        type: "DELETE",
                        dataType: "json",
                        success: function (json) {
                            console.log("DELETED")
                        },
                        error: function (xhr, status,
                                         errorThrown) {
                            console.log("DEL Error")
                        },
                        complete: function (xhr, status) {
                            location.reload();
                        }
                    });
                });

                body.append(newBook);
                body.append(newButton);
                body.append(newDiv);
            }
        },
        error: function (xhr, status,
                         errorThrown) {
        },
        complete: function (xhr, status) {
        }
    });

    var button = $("#button").attr("data-type", "POST");
    button.on("click", function (event) {
        event.preventDefault();
        var isbn = $("#isbn").val();
        var title = $("#title").val();
        var publisher = $("#publisher").val();
        var type = $("#type").val();
        var author = $("#author").val();

        $.ajax({
            url: "http://localhost:8080/books/",
            data: JSON.stringify({
                "isbn": isbn,
                "title": title,
                "publisher": publisher,
                "type": type,
                "author": author
            }),
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {
            },
            error: function (xhr, status,
                             errorThrown) {
            },
            complete: function (xhr, status) {
                location.reload();
            }
        });
    });
});