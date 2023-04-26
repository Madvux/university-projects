/*!
 * Start Bootstrap - Agency v7.0.0 (https://startbootstrap.com/theme/agency)
 * Copyright 2013-2021 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
 */
//
// Scripts
// 

class Zamowienie {
    constructor(dane, adres, telefon, email, typ, zestaw, liczbaOsob, pakiet, alergeny) {
        this.dane = dane;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.typ = typ;
        this.zestaw = zestaw;
        this.liczbaOsob = liczbaOsob;
        this.pakiet = pakiet;
        this.alergeny = alergeny;
    }
    pokaz() {
        return this.dane + this.adres + this.telefon + this.email + this.zestaw + this.liczbaOsob + this.pakiet + this.alergeny;
    }
}

window.addEventListener('DOMContentLoaded', event => {

// Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink');
        } else {
            navbarCollapsible.classList.add('navbar-shrink');
        }

    };
    // Shrink the navbar 
    navbarShrink();
    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);
    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74
        });
    }
    ;
    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
            document.querySelectorAll('#navbarResponsive .nav-link')
            );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

//##########################################################################
    var modal1 = document.getElementById("modal1");
    modal1.addEventListener('click', function () {
        fetch("http://localhost:8383/project/assets/modals/modal1.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal1x").innerHTML = html;
                });
    }, false);

    var modal2 = document.getElementById("modal2");
    modal2.addEventListener('click', function () {
        fetch("http://localhost:8383/project/assets/modals/modal2.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal2x").innerHTML = html;
                });
    }, false);

    var modal3 = document.getElementById("modal3");
    modal3.addEventListener('click', function () {
        fetch("http://localhost:8383/project/assets/modals/modal3.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal3x").innerHTML = html;
                });
    }, false);

    var modal4 = document.getElementById("modal4");
    modal4.addEventListener('click', function () {
        fetch("http://localhost:8383/project/assets/modals/modal4.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal4x").innerHTML = html;
                });
    }, false);

    var modal5 = document.getElementById("modal5");
    modal5.addEventListener('click', function () {
        fetch("http://localhost:8383/project/assets/modals/modal5.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal5x").innerHTML = html;
                });
    }, false);

    var modal6 = document.getElementById("modal6");
    modal6.addEventListener('click', function () {
        fetch("http://localhost:8383/project/assets/modals/modal6.html")
                .then(response => {
                    return response.text();
                })
                .then(html => {
                    document.getElementById("modal6x").innerHTML = html;
                });
    }, false);

//##########################################################################

    var zestaw = document.querySelectorAll('input[type=radio][name="zestaw"]');
    zestaw[1].checked = true;
    var maskowanie = document.querySelectorAll('tr.visible');
    zestaw.forEach(radio => radio.addEventListener('change', () => {
            for (i = 0; i < maskowanie.length; i++) {
                if (radio.value === "true")
                    maskowanie[i].classList.add("visually-hidden");
                else {
                    maskowanie[i].classList.remove("visually-hidden");
                }
            }
        }));
    var alergeny = document.querySelectorAll('input[type=checkbox][name="alergeny"]');
    var disabledchk = document.getElementById("allergies");
    disabledchk.disabled = true;
    alergeny.forEach(radio => radio.addEventListener('change', () => {
            if (alergeny[4].checked) {
                disabledchk.disabled = false;
            } else {
                disabledchk.disabled = true;
            }

        }));
    var klient = new Zamowienie;
    clear.addEventListener("click", () => {
        localStorage.clear();
    });
    show.addEventListener("click", () => {
        if (!localStorage.length) {
            document.getElementById("display").innerHTML = "Brak zleceń!";
        } else {
            document.getElementById("display").innerHTML = "";
            for (var i = 0; i < localStorage.length; i++)
            {
                let klucz = localStorage.key(i);
                let wartosc = JSON.parse(localStorage.getItem(klucz));
                document.getElementById("display").innerHTML += "<p>";
                document.getElementById("display").innerHTML += '<button onclick="usun(' + i + ')"><i class="fas fa-times"></i></button><br />';
                document.getElementById("display").innerHTML += "Dane: <b>" + wartosc.dane + "</b><br />";
                document.getElementById("display").innerHTML += "Adres: <b>" + wartosc.adres + "</b><br />";
                document.getElementById("display").innerHTML += "Telefon: <b>" + wartosc.telefon + "</b><br />";
                document.getElementById("display").innerHTML += "Email: <b>" + wartosc.email + "</b><br />";
                document.getElementById("display").innerHTML += "Typ: <b>" + wartosc.typ + "</b><br />";
                document.getElementById("display").innerHTML += "Zestaw: <b>" + wartosc.zestaw + "</b><br />";
                document.getElementById("display").innerHTML += "Liczba osób: <b>" + wartosc.liczbaOsob + "</b><br />";
                document.getElementById("display").innerHTML += "Pakiet: <b>" + wartosc.pakiet + "</b><br />";
                document.getElementById("display").innerHTML += "Alergeny: <b>" + wartosc.alergeny + "</b><br />";
                document.getElementById("display").innerHTML += "</p>";
            }
        }
    });

    send.addEventListener("click", () => {
        klient.dane = document.getElementById("dane").value;
        klient.adres = document.getElementById("adres").value;
        telefon = document.getElementById("telefon").value;
        klient.telefon = telefon.replace(/[- ]/g, "");
        klient.email = document.getElementById("email").value;
        klient.typ = document.getElementById("typ").value;
        let zestaw = document.getElementsByName("zestaw");


        klient.zestaw = [];
        for (i = 0; i < zestaw.length; i++) {
            if (zestaw[i].checked)
                klient.zestaw.push(zestaw[i].value);
        }

        if (zestaw[1].checked) {
            klient.liczbaOsob = document.getElementById("liczbaOsob").value;
            let pakiet = document.getElementsByName("pakiet");
            let alergeny = document.getElementsByName("alergeny");
            klient.pakiet = [];
            for (i = 0; i < pakiet.length; i++) {
                if (pakiet[i].checked)
                    klient.pakiet.push(pakiet[i].value);
            }

            klient.alergeny = [];
            for (i = 0; i < alergeny.length - 1; i++) {
                if (alergeny[i].checked) {
                    klient.alergeny.push(alergeny[i].value);
                }
            }
            if (alergeny[alergeny.length - 1].checked) {
                klient.alergeny.push(document.getElementById("allergies").value);
            }
        } else {
            klient.liczbaOsob = "-";
            klient.pakiet = "-";
            klient.alergeny = "-";
        }

        if (zestaw[0].checked) {
            if (walidacja_1(klient)) {
                localStorage.setItem(klient.telefon, JSON.stringify(klient));

            }
        } else {
            if (walidacja_1(klient) && walidacja_2(klient)) {
                localStorage.setItem(klient.telefon, JSON.stringify(klient));
            }
        }
    });

    filtr.addEventListener("input", () => {
        var filtr = document.getElementById('filtr').value;
        var filtrUP = filtr.toUpperCase();

        document.getElementById("display").innerHTML = "";
        for (var i = 0; i < localStorage.length; i++)
        {
            let klucz = localStorage.key(i);
            let wartosc = JSON.parse(localStorage.getItem(klucz));
            if (wartosc.dane.toString().toUpperCase().indexOf(filtrUP) > -1) {
                document.getElementById("display").innerHTML += "<p>";
                document.getElementById("display").innerHTML += '<button onclick="usun(' + i + ')"><i class="fas fa-times"></i></button><br />';
                document.getElementById("display").innerHTML += "Dane: <b>" + wartosc.dane + "</b><br />";
                document.getElementById("display").innerHTML += "Adres: <b>" + wartosc.adres + "</b><br />";
                document.getElementById("display").innerHTML += "Telefon: <b>" + wartosc.telefon + "</b><br />";
                document.getElementById("display").innerHTML += "Email: <b>" + wartosc.email + "</b><br />";
                document.getElementById("display").innerHTML += "Typ: <b>" + wartosc.typ + "</b><br />";
                document.getElementById("display").innerHTML += "Zestaw: <b>" + wartosc.zestaw + "</b><br />";
                document.getElementById("display").innerHTML += "Liczba osób: <b>" + wartosc.liczbaOsob + "</b><br />";
                document.getElementById("display").innerHTML += "Pakiet: <b>" + wartosc.pakiet + "</b><br />";
                document.getElementById("display").innerHTML += "Alergeny: <b>" + wartosc.alergeny + "</b><br />";
                document.getElementById("display").innerHTML += "</p>";
            }
        }

    });

    function walidacja_1(e) {

        var ok = true;
        obiektDane = /^[A-ZĄĘŁÓŻŹĆŃa-ząęśćżźńół ]{3,40}$/; //wyrażenie regularne dla nazwiska
        obiektEmail = /^([a-zA-Z])([.a-zA-Z0-9_-]){2,}\+{0,1}([.a-zA-Z0-9_-])*@([a-zA-Z])([.a-zA-Z0-9_-]){2,}\.{0,1}([a-zA-Z])+$/;
        obiektTelefon = /^\d{3}[- ]?\d{3}[- ]?\d{3}$|^\d{2}[- ]?\d{3}[- ]?\d{2}[- ]?\d{2}$/;
        obiektAdres = /[-A-ZĄĘŁÓŻŹĆŃa-ząęśćżźńół0-9.]{3,70}/;
        if (sprawdzPole(e.dane, obiektDane)) {
            document.getElementById("error_dane").classList.remove("text-danger");
            document.getElementById("error_dane").classList.add("text-success");
            document.getElementById("error_dane").innerHTML = '<i class="fas fa-check"></i>';
        } else {
            ok = false;
            document.getElementById("error_dane").classList.remove("text-success");
            document.getElementById("error_dane").classList.add("text-danger");
            document.getElementById("error_dane").innerHTML = '<i class="fas fa-times"></i>';
        }
        if (sprawdzPole(e.adres, obiektAdres)) {
            document.getElementById("error_adres").classList.remove("text-danger");
            document.getElementById("error_adres").classList.add("text-success");
            document.getElementById("error_adres").innerHTML = '<i class="fas fa-check"></i>';
        } else {
            ok = false;
            document.getElementById("error_adres").classList.remove("text-success");
            document.getElementById("error_adres").classList.add("text-danger");
            document.getElementById("error_adres").innerHTML = '<i class="fas fa-times"></i>';
        }

        if (sprawdzPole(e.email, obiektEmail)) {
            document.getElementById("error_email").classList.remove("text-danger");
            document.getElementById("error_email").classList.add("text-success");
            document.getElementById("error_email").innerHTML = '<i class="fas fa-check"></i>';
        } else {
            ok = false;
            document.getElementById("error_email").classList.remove("text-success");
            document.getElementById("error_email").classList.add("text-danger");
            document.getElementById("error_email").innerHTML = '<i class="fas fa-times"></i>';
        }

        if (sprawdzPole(e.telefon, obiektTelefon)) {
            document.getElementById("error_telefon").classList.remove("text-danger");
            document.getElementById("error_telefon").classList.add("text-success");
            document.getElementById("error_telefon").innerHTML = '<i class="fas fa-check"></i>';
        } else {
            ok = false;
            document.getElementById("error_telefon").classList.remove("text-success");
            document.getElementById("error_telefon").classList.add("text-danger");
            document.getElementById("error_telefon").innerHTML = '<i class="fas fa-times"></i>';
        }

        return ok;
    }
    function walidacja_2(e) {
        var ok = true;

        if (e.pakiet.length) {
            document.getElementById('error_pakiet').classList.remove("text-danger");
            document.getElementById('error_pakiet').classList.add("text-success");
            document.getElementById('error_pakiet').innerHTML = '<i class="fas fa-check"></i>';
        } else {
            ok = false;
            document.getElementById('error_pakiet').classList.remove("text-success");
            document.getElementById('error_pakiet').classList.add("text-danger");
            document.getElementById('error_pakiet').innerHTML = '<i class="fas fa-times"></i>';
        }

        return ok;
    }


});
function sprawdzPole(e, obiektRegex) {
    if (!obiektRegex.test(e))
        return (false);
    else
        return (true);
}

function usun(i)
{
    let klucz = localStorage.key(i);
    localStorage.removeItem(klucz);
    //filtr.initEvent('input');
    show.dispatchEvent(new CustomEvent("click"));
}
function modyfikujProdukt(i)
{
    var lista = JSON.parse(localStorage.getItem('lista'));
    //usuń i-ty element z listy zadań:
    //while (lista[i].nazwa === null || lista[i].nazwa === "") {
    lista[i].nazwa = prompt("Podaj nazwę produktu");
    lista[i].cena = prompt("Podaj cenę produktu");
    //dodaj kolejne właściwości do obiektu item:
    lista[i].kolor = prompt("Podaj kolor produktu");
    lista[i].liczba_sztuk = prompt("Podaj ilość");
    //}

    //zapisz zaktualizowaną listę w localStorage:
    localStorage.setItem('lista', JSON.stringify(lista)); //zapisz listę
    loadLocal(); //zaktualizuj widok na stronie
}
