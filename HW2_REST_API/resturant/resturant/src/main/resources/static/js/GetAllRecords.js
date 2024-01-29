// Function to retrieve all menu items from the server and populate the table
function retrieveAllItems() {
    // Fetch data from the '/Restaurant' endpoint using GET method
    fetch('/Restaurant', {
            method: 'GET',
        })
        .then(response => response.json()) // Parse the response as JSON
        .then(data => {
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
                const actionsCell = document.createElement('td');

                // Set cell content with item data
                idCell.textContent = item.id;
                nameCell.textContent = item.name;
                sizeCell.textContent = item.size;
                typeCell.textContent = item.type;
                priceCell.textContent = item.price;
                chefFavoriteCell.textContent = item.chefFavorite;

                // Create update button with click event listener
                const updateButton = document.createElement('button');
                updateButton.textContent = 'Update';
                updateButton.addEventListener('click', () => {
                    // Handle update logic, such as redirecting to an update page or showing a form in a modal
                    redirectToUpdateRecordPage(item);
                });

                // Create delete button with click event listener
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';
                deleteButton.addEventListener('click', () => {
                    // Call deleteItem() function to delete the item by its ID
                    deleteItem(item.id);
                });

                // Append buttons to actions cell
                actionsCell.appendChild(updateButton);
                actionsCell.appendChild(deleteButton);

                // Append cells to the row
                row.appendChild(idCell);
                row.appendChild(nameCell);
                row.appendChild(sizeCell);
                row.appendChild(typeCell);
                row.appendChild(priceCell);
                row.appendChild(chefFavoriteCell);
                row.appendChild(actionsCell);

                // Append row to the table body
                tableBody.appendChild(row);
            });

            // Retrieve and display the total record count
            retrieveRecordCount();
        })
        .catch(error => {
            // Handle errors by displaying an error message
            document.getElementById('response').innerHTML = `Error: ${error.message}`;
        });
}