const BASE_URL = "https://library-management-system-28b8.onrender.com";
const API_URL = BASE_URL + "/books";

document.addEventListener("DOMContentLoaded", () => {

    const loginBtn = document.getElementById("loginBtn");

    if (loginBtn) {
        loginBtn.addEventListener("click", () => {

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            const msg = document.getElementById("msg");

            if (username === "manager" && password === "manager123") {
                localStorage.setItem("user", "Manager");
                window.location.href = "add-book.html";
            } else if (username === "student" && password === "student123") {
                localStorage.setItem("user", "Student");
                window.location.href = "view-book.html";
            } else {
                msg.innerText = "Invalid Username or Password";
                msg.style.color = "red";
            }

        });
    }

    const addBookBtn = document.getElementById("addBookBtn");

    if (addBookBtn) {

        addBookBtn.addEventListener("click", async () => {

            const title = document.getElementById("title").value.trim();
            const author = document.getElementById("author").value.trim();
            const pdf = document.getElementById("pdf").files[0];
            const msg = document.getElementById("msg");

            if (!title || !author || !pdf) {
                msg.innerText = "Please fill all fields.";
                msg.style.color = "red";
                return;
            }

            const formData = new FormData();
            formData.append("title", title);
            formData.append("author", author);
            formData.append("pdf", pdf);

            try {

                const response = await fetch(API_URL + "/upload", {
                    method: "POST",
                    body: formData
                });

                const responseText = await response.text();

                if (!response.ok) {
                    throw new Error("Status: " + response.status + "\n\n" + responseText);
                }

                msg.innerText = "Book added successfully!";
                msg.style.color = "green";

                document.getElementById("title").value = "";
                document.getElementById("author").value = "";
                document.getElementById("pdf").value = "";

            } catch (error) {

                console.error(error);
                alert(error.message);

                msg.innerText = error.message;
                msg.style.color = "red";
            }

        });

    }

    const bookFields = document.getElementById("bookFields");

    if (bookFields) {

        fetch(API_URL)
            .then(res => res.json())
            .then(data => {

                bookFields.innerHTML = "";

                data.forEach(book => {

                    const pdfUrl = book.pdfPath
                        ? `${BASE_URL}/books/files/${book.pdfPath}`
                        : "#";

                    bookFields.innerHTML += `
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.issued ? "📕 Issued" : "✅ Available"}</td>
                            <td>
                                ${book.pdfPath
                                    ? `<a href="${pdfUrl}" target="_blank">View PDF</a>`
                                    : "No PDF"}
                            </td>
                        </tr>
                    `;
                });

            })
            .catch(err => {
                console.error(err);
            });

    }

});
