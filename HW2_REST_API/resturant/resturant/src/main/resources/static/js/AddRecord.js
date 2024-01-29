// Function to handle adding a new menu item
function addItem() {
    // Display a success message
    document.getElementById('response').innerHTML = 'Item added successfully!';
    
    // Get form data and create a new item object
    const formData = new FormData(document.getElementById('addItemForm'));
    const newItem = {
        name: formData.get('itemName'),
        size: formData.get('itemSize') || 'S',
        type: formData.get('itemType') || 'APPITIZER',
        price: parseFloat(formData.get('itemPrice')),
        chefFavorite: formData.get('chefFavorite') === 'true' ? true : false,
    };
    
    // Call API endpoint to add data
    fetch('/Restaurant', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newItem),
    })
    .then(response => response.text())
    .then(result => {
        // Display the API response
        document.getElementById('response').innerHTML = result;
        // Handle the response if needed
        console.log(result);
        // Redirect to another page after the API request is successful
        // window.location.href = '/index.html';
    })
    .catch(error => {
        // Display an error message if the API request fails
        document.getElementById('response').innerHTML = `Error: ${error.message}`;
        console.error('Error:', error);
    });
}

// Function to redirect to index.html page
function redirectToIndex() {
    window.location.href = '/index.html';
}
