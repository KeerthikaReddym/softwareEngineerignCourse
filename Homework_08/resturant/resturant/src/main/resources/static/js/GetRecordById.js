// Function to retrieve a menu item by its ID and display its details
function getRecordById() {
    // Get the record ID from the input field
    const recordId = document.getElementById('recordId').value;

    // Fetch the menu item from the API based on the provided ID
    fetch(`/Restaurant/${recordId}`, {
        method: 'GET',
    })
    .then(response => {
        // Check if the response is successful
        if (response.ok) {
            // Parse the response JSON data
            return response.json();
        } else {
            // If response is not successful, throw an error
            throw new Error('Failed to retrieve record');
        }
    })
    .then(record => {
        // Display the retrieved record details in the specified HTML element
        const recordDetailsDiv = document.getElementById('recordDetails');
        recordDetailsDiv.innerHTML = `
            <p>ID: ${record.id}</p>
            <p>Name: ${record.name}</p>
            <p>Size: ${record.size}</p>
            <p>Type: ${record.type}</p>
            <p>Price: ${record.price}</p>
            <p>Chef Favorite: ${record.chefFavorite}</p>
        `;
    })
    .catch(error => {
        // Handle errors and display an error message in the specified HTML element
        const recordDetailsDiv = document.getElementById('recordDetails');
        recordDetailsDiv.innerHTML = `Error: ${error.message}`;
    });
}
