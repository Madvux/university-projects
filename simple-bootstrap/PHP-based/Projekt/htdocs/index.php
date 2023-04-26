<?php

$zawartosc = "";

include 'skrypty/galeria.php';
include 'skrypty/services.php';
$zawartosc .= $services;
$zawartosc .= $galeria;

$zawartosc .= '<!-- About-->
        <section class="page-section" id="about">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">O Nas</h2>
                    <h3 class="section-subheading text-muted">Jesteśmy firmą cateringową oferującą zaopatrzenie:</h3>
                </div>
                <ul class="timeline">
                    <li>
                        <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/diet.jpg" alt="..." /></div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Diet indywidualnych</h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">Pakowane posiłki przygotowane <br />specjalne dla twoich potrzeb.</p></div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/wedding.jpg" alt="..." /></div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Wesel</h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">W tak specjalnym dniu nie wolno pozwolić sobie by jedzenie psuło atmosferę.</p></div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/communion.jpg" alt="..." /></div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Komunii</h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">Uroczystość z okazji przyjęcia sakramentu <br />powinna być niezapomnianym przeżyciem <br />- także w kwestii smaku.</p></div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/party.jpg" alt="..." /></div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Imprez okolicznościowych</h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">Urodziny, imieniny czy może bez okazji?</p></div>
                        </div>
                    </li>

                    <li>
                        <div class="timeline-image">
                            <h4>
                                oraz
                                <br />
                                wiele
                                <br />
                                więcej!
                            </h4>
                        </div>
                    </li>
                </ul>

            </div>
        </section>
';

$zawartosc .= '        <!-- Form modal popup -->
        <div class="portfolio-modal modal fade" id="login" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <div class="modal-body">
                                <form action="index.php" method="post">
                                    <table class="table">';

$zawartosc .= login_form();
$zawartosc .= '
                 </table>
                 </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
';
$zawartosc .= '        <!-- Form modal popup -->
        <div class="portfolio-modal modal fade" id="register" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <div class="modal-body">
                                <form action="index.php" method="post">
                                    <table class="table">';

$zawartosc .= register_form();

$zawartosc .= '
                 </table>
                     </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
';

include_once 'klasy/User.php';

function login_form() {
    $zawartosc = "";
    include_once 'klasy/UserManager.php';
    include_once 'klasy/Baza.php';
    $db = new Baza("localhost", "root", "", "projekt");
    $um = new UserManager();
    if (filter_input(INPUT_GET, "akcja") == "wyloguj") {
        $um->logout($db);
    }
    if (filter_input(INPUT_POST, "zaloguj")) {
        $userId = $um->login($db); //sprawdź parametry logowania
        if ($userId > 0) {
            $zawartosc .= header("location:zalogowany.php");
        } else {
            echo "<script>alert('Logowanie nie powiodło się!')</script>";

            $zawartosc .= "<p>Błędna nazwa użytkownika lub hasło</p>";
            $zawartosc .= $um->loginForm(); //Pokaż formularz logowania
        }
    } else {
        $zawartosc .= $um->loginForm();
    }
    return $zawartosc;
}

include_once 'skrypty/funkcje.php';
include_once 'klasy/Baza.php';
$baza = new Baza('localhost', 'root', '', 'projekt');
if (filter_input(INPUT_POST, 'send', FILTER_SANITIZE_FULL_SPECIAL_CHARS)) {
    dodaj_kontakt($baza);
}

function register_form() {
    $zawartosc = "";
    include_once 'klasy/Baza.php';
    $baza = new Baza('localhost', 'root', '', 'projekt');
    include_once 'klasy/RegistrationForm.php';
    $rf = new RegistrationForm(); //wyświetla formularz rejestracji
    $zawartosc .= $rf->__construct();
    if (filter_input(INPUT_POST, 'submit', FILTER_SANITIZE_FULL_SPECIAL_CHARS)) {
        $user = $rf->checkUser(); //sprawdza poprawność danych
        if ($user === NULL) {
            $zawartosc .= "<p>Niepoprawne dane rejestracji.</p>";
            echo "<script>alert(Niepoprawne dane rejestracji.)</script>";
        } else {
            $nazwa_u = $user->getUserName();
            $zawartosc .= "Zarejestrowano pomyślnie!<br>Możesz się teraz zalogować jako: " . $nazwa_u;
            echo "<script>alert(Zarejestrowano pomyślnie!<br>Możesz się teraz zalogować jako: " . $nazwa_u . ")</script>";
            $user->saveDB($baza);
        }
    }
    return $zawartosc;
}

include_once 'klasy/UserManager.php';
$um = new UserManager();
session_start();
$sessionId = session_id();
$userId = $um->getLoggedInUser($baza, $sessionId);
if ($userId > 0) {
    $zawartosc = header("location:zalogowany.php");
}
require_once("klasy/Strona.php");
$strona_akt = new Strona();
$plik = "index.php";
if (file_exists($plik)) {
    require_once($plik);
    $strona_akt->ustaw_zawartosc($zawartosc);
    $strona_akt->wyswietl_naglowek();
    $strona_akt->wyswietl_menu();
    $strona_akt->wyswietl_zawartosc();
    $strona_akt->wyswietl_stopke();
}