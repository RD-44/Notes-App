function submitForm(that) {
    const newContent = that.userinput.value
    if (newContent.length == 0){
        alert('Error - empty input.')
        return false;
    }
    return true;
}

function validateContent(that){ // Maybe implement a simp
    if (!submitForm(that)){
        return false;
    }
    if (that.add.value.includes('url')) {
        alert('url.');
    }
    return true;
}