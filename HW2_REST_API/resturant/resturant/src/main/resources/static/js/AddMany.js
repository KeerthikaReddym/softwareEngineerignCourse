// Function to add multiple menu items to the database
function addManyItems() {
    // Get JSON data from the textarea
    const jsonData = document.getElementById('jsonData').value;

    // Parse the JSON data into JavaScript objects
    const data = JSON.parse(jsonData);

    // Send a POST request to the '/Restaurant/AddMany' endpoint with the JSON data
    fetch('/Restaurant/AddMany', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => response.text())
        .then(result => {
            // Update the response element with the result message
            document.getElementById('response').innerHTML = result;
            // Clear the textarea after successful addition
            document.getElementById('jsonData').value = '';
            // Retrieve and update all menu items in the table after adding new items
            retrieveAllItems();
            // Retrieve and update the total record count
            retrieveRecordCount();
        })
        .catch(error => {
            // Handle errors by displaying an error message
            document.getElementById('response').innerHTML = `Error: ${error.message}`;
        });
}
