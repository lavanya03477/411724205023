document.getElementById("loadBtn").addEventListener("click", () => {
  const statusDiv = document.getElementById("status");
  const usersDiv = document.getElementById("users");
  statusDiv.textContent = "Loading...";
  usersDiv.innerHTML = "";
  setTimeout(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(response => response.json())
      .then(data => {
        data.forEach(user => {
          const card = document.createElement("div");
          card.className = "user-card";
          card.innerHTML = `
            <h3>${user.name}</h3>
            <p>Email: ${user.email}</p>
            <p>Phone: ${user.phone}</p>
          `;
          usersDiv.appendChild(card);
        });
        statusDiv.textContent = "Loaded successfully!";
      })
      .catch(error => {
        statusDiv.textContent = "Error loading data";
        console.error(error);
      });
  }, 2000); 
});
