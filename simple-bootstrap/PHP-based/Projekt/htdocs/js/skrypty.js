//Fetch API
document.addEventListener('DOMContentLoaded', () => {
    var pokaz_moje_zamowienia = document.getElementById('pokaz_moje_zamowienia');
    pokaz_moje_zamowienia.addEventListener("click", () => {
        pokaz1();
    });
    var pokaz_zalogowanych = document.getElementById('pokaz_zalogowanych');
    pokaz_zalogowanych.addEventListener("click", () => {
        pokaz2();
    });
    var pokaz_zainteresowanych = document.getElementById('pokaz_zainteresowanych');
    pokaz_zainteresowanych.addEventListener("click", () => {
        pokaz3();
    });
    var pokaz_zamowienia = document.getElementById('pokaz_zamowienia');
    pokaz_zamowienia.addEventListener("click", () => {
        pokaz4();
    });
});
function pokaz1() {
    fetch("http://localhost/skrypty/pokaz_moje_zamowienia.php")
            .then((response) => {
                if (response.status !== 200) {
                    return Promise.reject('Coś poszło nie tak!');
                }
                return response.text();
            })
            .then((data) => {
                document.getElementById('pokaz_wybrany').innerHTML = data;
            })
            .catch((error) => {
                console.log(error);
            });
}

function pokaz2() {
    fetch("http://localhost/skrypty/pokaz_zalogowanych.php")
            .then((response) => {
                if (response.status !== 200) {
                    return Promise.reject('Coś poszło nie tak!');
                }
                return response.text();
            })
            .then((data) => {
                document.getElementById('pokaz_wybrany').innerHTML = data;
            })
            .catch((error) => {
                console.log(error);
            });
}
function pokaz3() {
    fetch("http://localhost/skrypty/pokaz_zainteresowanych.php")
            .then((response) => {
                if (response.status !== 200) {
                    return Promise.reject('Coś poszło nie tak!');
                }
                return response.text();
            })
            .then((data) => {
                document.getElementById('pokaz_wybrany').innerHTML = data;
            })
            .catch((error) => {
                console.log(error);
            });
}

function pokaz4() {
    fetch("http://localhost/skrypty/pokaz_zamowienia.php")
            .then((response) => {
                if (response.status !== 200) {
                    return Promise.reject('Coś poszło nie tak!');
                }
                return response.text();
            })
            .then((data) => {
                document.getElementById('pokaz_wybrany').innerHTML = data;
            })
            .catch((error) => {
                console.log(error);
            });
}
