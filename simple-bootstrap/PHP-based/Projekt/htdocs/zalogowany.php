<?php

$zawartosc = '<!-- Orders-->
        <section class="page-section" id="orders">
            <div class="container">
                <div class="row text-center">
                    <div class="col-md-12">';


include_once 'klasy/Baza.php';
include_once 'klasy/User.php';
include_once 'klasy/UserManager.php';
$db = new Baza("localhost", "root", "", "projekt");
$um = new UserManager();
session_start();
$sessionId = session_id();
$userId = $um->getLoggedInUser($db, $sessionId);
if ($userId > 0) {//zalogowany
    $zawartosc .= "<p>Poprawne logowanie.<br />";
    $zawartosc .= "Zalogowany użytkownik o id=$userId <br />";
    $zawartosc .= "<h3>Dane zalogowanego użytkownika: </h3>";
    $sql = "SELECT * FROM users WHERE id=$userId";
    $pola = ["id", "userName", "fullName", "email"];
    $zawartosc .= $db->select($sql, $pola);
    $zawartosc .= '<br>';
    if (filter_input(INPUT_POST, 'zamow', FILTER_SANITIZE_FULL_SPECIAL_CHARS)) {
        include_once 'skrypty/funkcje.php';
        dodaj_zamowienie($db, $userId);
    }
    
    $sql = "SELECT * FROM users WHERE id=$userId AND status=1";
    $result = $db->getMysqli()->query($sql);
    if ($result->num_rows > 0) {//jako user

        $zawartosc .= "<h3>Moje zamówienia:</h3>";
        $zawartosc .= "<div class='row align-items-stretch mb-5'><div class='col-md-6'><h3>W realizacji:</h3>";
        $sql = "select *  FROM zamowienia where userId=$userId AND status = 0 ORDER BY data DESC";
        $pola = ["id", "typ", "liczbaOsob", "pakiet", "data"];
        $zawartosc .= $db->select($sql, $pola);
        $zawartosc .= "</div><div class='col-md-6'><h3>Zrealizowane:</h3>";
        $sql = "select *  FROM zamowienia where userId=$userId AND status = 1 ORDER BY data DESC";
        $pola = ["id", "typ", "liczbaOsob", "pakiet", "data"];
        $zawartosc .= $db->select($sql, $pola);
        $zawartosc .= "</div></div>";

    }

    
    $sql = "SELECT * FROM users WHERE id=$userId AND status=2";
    $result = $db->getMysqli()->query($sql);
    if ($result->num_rows > 0) {//jako admin
        if (isset($_POST['nrzamowienia']) && ($_POST['nrzamowienia'] != "")) {
            $x = htmlspecialchars(trim($_POST['nrzamowienia']));
            $sql = "UPDATE zamowienia SET status=true where id=$x";
            $result = $db->getMysqli()->query($sql);
        }
        $zawartosc .= '<table class="table"><tr>';
        $zawartosc .= '<td><button class="btn btn-dark"  id="pokaz_moje_zamowienia">Moje zamówienia</button></td>';
        $zawartosc .= '<td><button class="btn btn-secondary" id="pokaz_zalogowanych">Pokaż zalogowanych</button></td>';
        $zawartosc .= '<td><button class="btn btn-dark" id="pokaz_zamowienia">Pokaż zamówienia</button></td>';
        $zawartosc .= '<td><button class="btn btn-secondary" id="pokaz_zainteresowanych">Pokaż zainteresowanych</button></td>';
        $zawartosc .= '</tr><tr><td colspan="4"><div id="pokaz_wybrany"></div></td>';
        $zawartosc .= '</tr></table>';
    }
} else {
    $zawartosc .= header("location:index.php");
}

$zawartosc .= ' </div></div></div></section>';



require_once("klasy/Strona.php");
$strona_akt = new Strona();
$plik = "zalogowany.php";
if (file_exists($plik)) {
    require_once($plik);
    $strona_akt->ustaw_zawartosc($zawartosc);
    $strona_akt->wyswietl_naglowek();
    $strona_akt->wyswietl_menu_zalogowany();
    $strona_akt->wyswietl_zawartosc_zalogowany();
    $strona_akt->wyswietl_stopke();
}

    