// Function to delete a specific menu item by its ID
function deleteItem(id) {
	fetch(`/Restaurant/${id}`, {
		method: 'DELETE',
	})
	.then(response => {
		if (response.ok) {
			// Item deleted successfully, update the table and record count
			retrieveAllItems(); // Update the table
			retrieveRecordCount(); // Update the total record count
		} else {
			throw new Error('Failed to delete item');
		}
	})
	.catch(error => {
		document.getElementById('response').innerHTML = `Error: ${error.message}`;
	});
}

// Function to retrieve and display the total record count
function retrieveRecordCount() {
	fetch('/Restaurant/Count', {
		method: 'GET',
	})
	.then(response => response.text())
	.then(count => {
		if (count === '') {
			count = 0;
		}
		document.getElementById('recordCount').textContent = `Total Records: ${count}`;
	})
	.catch(error => {
		document.getElementById('response').innerHTML = `Error: ${error.message}`;
	});
}

// Function to redirect to the page for adding a single menu item
function addSingleItemPopup() {
	// Redirect to the Addrecord.html page for adding a single item
	window.location.href = '/Addrecord.html';
}

// Function to redirect to the Get Record by ID page
function redirectToGetRecordPage() {
	// Redirect to the GetRecordById.html page for retrieving a record by ID
	window.location.href = '/GetRecordById.html';
}

// Function to delete all menu items
function deleteAllItems() {
	fetch('/Restaurant', {
		method: 'DELETE', // Send a DELETE request to delete all records
	})
	.then(response => {
		if (response.ok) {
			// Records deleted successfully, update the table and record count
			retrieveAllItems(); // Update the table
			retrieveRecordCount(); // Update the total record count
		} else {
			throw new Error('Failed to delete records');
		}
	})
	.catch(error => {
		document.getElementById('response').innerHTML = `Error: ${error.message}`;
	});
}

// Function to redirect to the Update Record by ID page and store the selected item in sessionStorage
function redirectToUpdateRecordPage(item) {
	// Store the selected item in sessionStorage to access it in the update page
	sessionStorage.setItem('selectedItem', JSON.stringify(item));
	// Redirect to the UpdateById.html page for updating the selected item
	window.location.href = '/UpdateById.html';
}
