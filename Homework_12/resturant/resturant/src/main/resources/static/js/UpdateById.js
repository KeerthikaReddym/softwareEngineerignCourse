// Retrieve the selected item from sessionStorage
const selectedItem = JSON.parse(sessionStorage.getItem('selectedItem'));

// Populate the form fields with the selected item's data
document.getElementById('id').value = selectedItem.id;
document.getElementById('name').value = selectedItem.name;
document.getElementById('size').value = selectedItem.size;
document.getElementById('type').value = selectedItem.type;
document.getElementById('price').value = selectedItem.price;
document.getElementById('chefFavorite').value = selectedItem.chefFavorite;

// Function to update the selected record
function updateRecord() {
    // Get data from form fields
    const id = document.getElementById('id').value;
    const name = document.getElementById('name').value;
    const size = document.getElementById('size').value;
    const type = document.getElementById('type').value;
    const price = document.getElementById('price').value;
    const chefFavorite = document.getElementById('chefFavorite').value;

    // Send a PUT request to update the record
    fetch(`/Restaurant/${selectedItem.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: id,
            name: name,
            size: size,
            type: type,
            price: price,
            chefFavorite: chefFavorite
        }),
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Failed to update record');
        }
    })
    .then(result => {
        // Display update result message
        const recordDetailsDiv = document.getElementById('recordDetails');
        recordDetailsDiv.innerHTML = `Update Successful: ${result}`;
    })
    .catch(error => {
        // Handle errors and display error message
        const recordDetailsDiv = document.getElementById('recordDetails');
        recordDetailsDiv.innerHTML = `Error: ${error.message}`;
    });
}

// Function to redirect to index.html page
function redirectToIndex() {
    window.location.href = '/index.html';
}
