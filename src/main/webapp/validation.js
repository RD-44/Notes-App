function submitForm(that) {
    // Simple validation for empty user inputs.
    const newContent = String(that.userinput.value);
    if (newContent.length == 0){
        alert('Error - empty input.')
        return false;
    }
    return true;
}