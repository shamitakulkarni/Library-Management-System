const API_URL = "http://localhost:8081/books";

// Ensure the DOM is fully loaded before executing scripts
document.addEventListener('DOMContentLoaded', () => {

    // ==========================================
    // 1. LOGIN PAGE LOGIC
    // ==========================================
    const loginBtn = document.getElementById("loginBtn");
    if (loginBtn) {
        loginBtn.addEventListener("click", () => {
            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;
            let msg = document.getElementById("msg");

            if (username === "manager" && password === "manager123") {
                localStorage.setItem("user", "Manager");
                window.location.href = "/add-book.html";
            } else if (username === "student" && password === "student123") {
                localStorage.setItem("user", "Student");
                window.location.href = "/view-book.html";
            } else {
                msg.innerText = "Invalid Username or Password";
                msg.style.color = "red";
            }
        });
    }

    // ==========================================
    // 2. ADD BOOK PAGE LOGIC (UPLOAD PDF)
    // ==========================================
    const addBookBtn = document.getElementById("addBookBtn");
    if (addBookBtn) {
        addBookBtn.addEventListener("click", () => {
            let title = document.getElementById("title").value;
            let author = document.getElementById("author").value;
            let pdf = document.getElementById("pdf").files[0];
            let msg = document.getElementById("msg");

            if (!title || !author || !pdf) {
                msg.innerText = "Please fill all fields!";
                msg.style.color = "red";
                return;
            }

            let formData = new FormData();
            formData.append("title", title);
            formData.append("author", author);
            formData.append("pdf", pdf);

            fetch(API_URL + "/upload", {
                method: "POST",
                body: formData
            })
            .then(res => {
                if (!res.ok) {
                    throw new Error("Network response was not ok");
                }
                return res.json();
            })
            .then(data => {
                msg.innerText = "Book added successfully!";
                msg.style.color = "green";

                // Clear fields safely
                document.getElementById("title").value = "";
                document.getElementById("author").value = "";
                document.getElementById("pdf").value = "";
            })
            .catch(err => {
                console.error(err);
                msg.innerText = "Error adding book!";
                msg.style.color = "red";
            });
        });
    }

    // ==========================================
    // 3. VIEW BOOKS PAGE LOGIC (Fixed to target bookFields)
    // ==========================================
    const bookFields = document.getElementById("bookFields");
    if (bookFields) {
        // Automatically fetch and render books when view-book.html loads
        fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            bookFields.innerHTML = ""; // Clears old data rows, keeping <thead> intact!

            data.forEach(book => {
                bookFields.innerHTML += `
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.issued ? "📕 Issued" : "✅ Available"}</td>
                        <td>
                            <a href="${book.pdfPath ? 'http://localhost:8081/uploads/' + book.pdfPath : '#'}" target="_blank">
                                View PDF
                            </a>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error("Error loading books:", err);
        });
    }

});