// Function to retrieve menu items by type from the server and display in the table
function retrieveAllItemsByType() {
    // Get selected type from the filter dropdown
    const selectedType = document.getElementById('typeFilterTableHeader').value;

    // Create headers with selected type
    const headers = new Headers();
    headers.append('type', selectedType);

    // Fetch data from the '/Restaurant/type' endpoint using GET method with headers
    fetch('http://localhost:8089/Restaurant/type', {
            method: 'GET',
            headers: headers
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Call displayData() to populate the table with retrieved items
            displayData(data);
        })
        .catch(error => {
            // Handle errors by displaying an error message
            document.getElementById('response').innerHTML = `Error: ${error.message}`;
        });
}

// Function to retrieve menu items by name from the server and display in the table
function retrieveAllItemsByName() {
    // Get selected name from the input field
    const selectedName = document.getElementById('itemName').value;

    // Fetch data from the '/Restaurant/getByName/:name' endpoint using GET method
    fetch(`/Restaurant/getByName/${selectedName}`, {
            method: 'GET'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Call displayData() to populate the table with retrieved items
            displayData(data);
        })
        .catch(error => {
            // Handle errors by displaying an error message
            document.getElementById('response').innerHTML = `Error: ${error.message}`;
        });
}

// Function to retrieve menu items by price from the server and display in the table
function retrieveAllItemsByPrice() {
    // Get selected price from the input field
    const selectedPrice = document.getElementById('itemPrice').value;

    // Fetch data from the '/Restaurant/filter/:price' endpoint using GET method
    fetch(`/Restaurant/filter/${selectedPrice}`, {
            method: 'GET'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Call displayData() to populate the table with retrieved items
            displayData(data);
        })
        .catch(error => {
            // Handle errors by displaying an error message
            document.getElementById('response').innerHTML = `Error: ${error.message}`;
        });
}

// Function to display menu items in the table
function displayData(data) {
    const tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = ''; // Clear existing rows

    // Iterate through each item in the data and create table rows
    data.forEach(item => {
        const row = document.createElement('tr');
        const idCell = document.createElement('td');
        const nameCell = document.createElement('td');
        const sizeCell = document.createElement('td');
        const typeCell = document.createElement('td');
        const priceCell = document.createElement('td');
        const chefFavoriteCell = document.createElement('td');

        // Set cell content with item data
        idCell.textContent = item.id;
        nameCell.textContent = item.name;
        sizeCell.textContent = item.size;
        typeCell.textContent = item.type;
        priceCell.textContent = item.price;
        chefFavoriteCell.textContent = item.chefFavorite;

        // Append cells to the row
        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(sizeCell);
        row.appendChild(typeCell);
        row.appendChild(priceCell);
        row.appendChild(chefFavoriteCell);

        // Append row to the table body
        tableBody.appendChild(row);
    });
}

// Function to retrieve menu items marked as chef favorites from the server and display in the table
function retrieveAllItemsByChef() {
    // Fetch data from the '/Restaurant/filter/chefFavorite' endpoint using GET method
    fetch('/Restaurant/filter/chefFavorite', {
            method: 'GET'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Call displayData() to populate the table with retrieved items
            displayData(data);
        })
        .catch(error => {
            // Handle errors by displaying an error message
            document.getElementById('response').innerHTML = `Error: ${error.message}`;
        });
}
