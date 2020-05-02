var cards = document.getElementsByClassName("card");
var cardsPalette = document.getElementsByClassName("card_palette");
var lieferant = "";


var inputLieferant = document.createElement('input');
var inputArtikel = document.createElement('input');
var buttonLieferant = document.getElementById('lieferantClick')
var buttonArtikel = document.getElementById('artikelClick');
inputLieferant.type = 'file';
inputArtikel.type = 'file';
var fileLieferant;
var fileArtikel;

inputLieferant.onchange = e => {
   fileLieferant = e.target.files[0];
   console.log("test");

   if(fileLieferant.name.endsWith('.csv'))
   {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
                if (xhttp.status == 200)
                {
                    alert("Datei '" + fileLieferant.name + "' Erfolgreich Hochgeladen");
                    location.reload();
                }
            }

        var formData = new FormData();

        formData.append("lieferant",fileLieferant);
        xhttp.open("POST","/lieferant");
        xhttp.send(formData);
   } else alert("es muss sich um eine CSV Datei Handeln");
}
inputArtikel.onchange = e => {
  fileArtikel = e.target.files[0];
  console.log("test");

  if(fileArtikel.name.endsWith('.csv'))
  {
       var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
               if (xhttp.status == 200)
               {
                   alert("Datei '" + fileArtikel.name + "' Erfolgreich Hochgeladen");
                   location.reload();
               }
               if (xhttp.status == 500)
               {
                   alert("Datei '" + fileArtikel.name + "' konnte nicht Hochgeladen werden! Fehlerhaftes CSV Format");
               }
           }

       var formData = new FormData();

       formData.append("palette",fileArtikel);
       xhttp.open("POST","/palette");
       xhttp.send(formData);
  } else alert("es muss sich um eine CSV Datei Handeln");
}

buttonLieferant.addEventListener('click', function()
{
    inputLieferant.click();
});
buttonArtikel.addEventListener('click', function()
{
    inputArtikel.click();
});


for(var i=0; i<cards.length; i++) {
    cards[i].addEventListener('click', function()
      {
          lieferant = "\"" + this.getAttribute('data') + "\"";
          window.location.href = this.getAttribute('data');
      });
}

for(var i=0; i<cardsPalette.length; i++) {
    cardsPalette[i].addEventListener('click', function()
      {
          window.location.href = this.getAttribute('data');
      });
}

var xhr = new XMLHttpRequest();
document.getElementById('export_all').addEventListener('click', function()
      {
        window.location.href = "/export/lieferanten.csv";
      });

document.getElementById('search').addEventListener('click', function()
    {
        if(document.getElementById('input').value != "")window.location.href = "/search_" + document.getElementById('input').value;
    });

if(document.getElementById('export_palette') != null) document.getElementById('export_palette').addEventListener('click', function()
      {
          window.location.href = "/export/" + this.getAttribute('data') + ".csv";
      });