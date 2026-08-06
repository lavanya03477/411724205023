let notes = [];

// CREATE
function addNote() {
  const input = document.getElementById('noteInput');
  const noteText = input.value.trim();
  if (noteText) {
    const note = { id: Date.now(), text: noteText };
    notes.push(note);
    input.value = "";
  }
}

// READ (triggered by Display button)
function renderNotes() {
  const notesDiv = document.getElementById('notes');
  notesDiv.innerHTML = ""; // clear previous
  notes.forEach(note => {
    const noteDiv = document.createElement('div');
    noteDiv.className = "note";
    noteDiv.innerHTML = `
      ${note.text}
      <button onclick="updateNote(${note.id})">Update</button>
      <button onclick="deleteNote(${note.id})">Delete</button>
    `;
    notesDiv.appendChild(noteDiv);
  });
}

// UPDATE
function updateNote(id) {
  const newText = prompt("Edit your note:");
  if (newText) {
    const note = notes.find(n => n.id === id);
    if (note) note.text = newText;
    renderNotes();
  }
}

// DELETE
function deleteNote(id) {
  notes = notes.filter(n => n.id !== id);
  renderNotes();
}

