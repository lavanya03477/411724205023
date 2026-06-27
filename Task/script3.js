const input = document.getElementById("messageInput");
const addBtn = document.getElementById("addBtn");
const clearBtn = document.getElementById("clearBtn");
const count = document.getElementById("count");
const messagesDiv = document.getElementById("messages");
const status = document.getElementById("status");

let messages = [];

// Character count
input.addEventListener("input", () => {
    count.textContent = input.value.length;
});

// Display all messages
function displayMessages() {
    messagesDiv.innerHTML = "";

    messages.forEach((msg, index) => {
        const div = document.createElement("div");
        div.textContent = msg;
        messagesDiv.appendChild(div);

        // Remove after 10 seconds
        setTimeout(() => {
            if (messages[index] === msg) {
                messages.splice(index, 1);
                displayMessages();
                status.textContent = "Message Expired";
            }
        }, 10000);
    });
}

// Add Message
addBtn.addEventListener("click", () => {
    const text = input.value.trim();

    const promise = new Promise((resolve, reject) => {
        if (text.length >= 3) {
            resolve(text);
        } else {
            reject("Message must contain at least 3 characters");
        }
    });

    promise
        .then((msg) => {
            status.textContent = "Message Added Successfully";
            messages.push(msg);
            displayMessages();

            input.value = "";
            count.textContent = "0";
        })
        .catch((error) => {
            status.textContent = error;
        });
});

// Clear All Messages
clearBtn.addEventListener("click", () => {
    messages = [];
    messagesDiv.innerHTML = "";
    status.textContent = "All Messages Cleared";
});