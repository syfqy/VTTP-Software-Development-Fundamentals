function incrementHehe() {
    var value = parseInt(document.getElementById('hehe-count').innerHTML);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('hehe-count').innerHTML = value;
}

function reset() {
    document.getElementById('hehe-count').innerHTML = 0;
}