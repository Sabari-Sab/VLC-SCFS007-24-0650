function updateBalanceAndId() {
    const accountDropdown = document.getElementById("from-account");
    const balanceField = document.getElementById("balance");
    const hiddenAccountIdField = document.getElementById("fromaccount-id");

    // Get the selected option
    const selectedOption = accountDropdown.options[accountDropdown.selectedIndex];

    // Get the balance from the data attribute
    const balance = selectedOption.getAttribute("data-balance");
    const fromaccountId = selectedOption.getAttribute("data-fromaccount-id");

    // Update the balance field
    balanceField.value = balance ? `Available Balance: $${balance}` : "";
    hiddenAccountIdField.value = fromaccountId || "";
}
function updateToAccId() {
    const accountDropdown = document.getElementById("to-account");
    const hiddenAccountIdField = document.getElementById("toaccount-id");

    // Get the selected option
    const selectedOption = accountDropdown.options[accountDropdown.selectedIndex];

    // Get the data attribute
    const toaccountId = selectedOption.getAttribute("data-toaccount-id");

    // Update the id in hidden input field
    hiddenAccountIdField.value = toaccountId || "";
}
function validateForm(event) {
    const fromAccountType = document.getElementById("from-account").value;
    const fromAccountId = document.getElementById("fromaccount-id").value;
    const toAccountType = document.getElementById("to-account").value;
    const toAccountId = document.getElementById("toaccount-id").value;
    const amount = document.getElementById("amount").value;

    let errorMessage = "";

    // Validate From Account
    if (!fromAccountType) {
        errorMessage += "Please select a 'From Account'.\n";
    }
    if (!fromAccountId) {
        errorMessage += "Invalid 'From Account' selection.\n";
    }

    // Validate To Account
    if (!toAccountType) {
        errorMessage += "Please select a 'To Account'.\n";
    }
    if (!toAccountId) {
        errorMessage += "Invalid 'To Account' selection.\n";
    }

    // Validate Amount
    if (!amount) {
        errorMessage += "Please enter an amount.\n";
    } else if (isNaN(amount) || parseFloat(amount) <= 0) {
        errorMessage += "Amount must be a positive number.\n";
    }

    // Display Error or Submit Form
    if (errorMessage) {
        alert(errorMessage);
        event.preventDefault(); // Prevent form submission
    }
}