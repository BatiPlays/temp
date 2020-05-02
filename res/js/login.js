var name = document.getElementById('name');
var password = document.getElementById('password');
var button = document.getElementById('button');

var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
        if (xhttp.status == 200)
        {
            alert("Angemeldet");
            location.reload();
        }
        if (xhttp.status == 404)
        {
            alert("User not found");
            location.reload();
        }
    }

button.addEventListener('click', function()
{
        xhttp.open("GET","/login/" + document.getElementById('name').value + "/" + document.getElementById('password').value);
    xhttp.send();
});


