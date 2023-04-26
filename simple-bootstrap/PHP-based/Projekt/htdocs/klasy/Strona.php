<?php

class Strona {

    protected $zawartosc;
    protected $slowa_kluczowe = "catering, projekt, php, jedzenie, diety, wesela, komunie";

    public function ustaw_zawartosc($nowa_zawartosc) {
        $this->zawartosc = $nowa_zawartosc;
    }

    public function ustaw_slowa_kluczowe($nowe_slowa) {
        $this->slowa_kluczowe = $nowe_slowa;
    }

    public function ustaw_style($url) {
        echo '<link rel="stylesheet" href="' . $url . '" type="text/css"/>';
    }

    public function wyswietl_slowa_kluczowe() {
        echo "<meta name=\"keywords\" content=\"$this->slowa_kluczowe\">";
    }

    public function wyswietl_menu() {
        ?>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="#page-top">
                    Jedzenie czeka!</a>
                <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#page-top">Oferta</a></li>
                        <li class="nav-item"><a class="nav-link" href="#portfolio">Galeria</a></li>
                        <li class="nav-item"><a class="nav-link" href="#about">O nas</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact">Kontakt</a></li>
                    </ul>
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item">
                            <button class="navbar-toggler" style="display: block;" data-bs-toggle="modal" href="#login" type="button">
                                Zaloguj
                                <i class="fas fa-power-off ms-1"></i>
                            </button>
                            <!--                            <a class="btn navbar-toggler" style="display: block;" data-bs-toggle="modal" href="#login">
                                                            Zaloguj
                                                            <i class="fas fa-power-off ms-1"></i>
                                                        </a>-->
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <?php
    }

    public function wyswietl_menu_zalogowany() {
        ?>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="#page-top">
                    Jedzenie czeka!</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#page-top">Zamów</a></li>
                        <li class="nav-item"><a class="nav-link" href="#orders">Moje zamówienia</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact">Kontakt</a></li>
                    </ul>
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item">
                            <a href="index.php?akcja=wyloguj" style="text-decoration: none">

                                <button class="navbar-toggler" style="display: block;color:#ffc800;" type="button">
                                    Wyloguj
                                    <i class="fas fa-power-off ms-1"></i>
                                </button></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <?php
    }

    public function wyswietl_naglowek() {
        ?>
        <!DOCTYPE html>
        <html lang="en">
            <head>
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Projekt - Programowanie aplikacji internetowych" />
                <meta name="author" content="Tomasz Wilkołazki" />
                <title>Catering - Jedzenie czeka!</title>
                <!-- Favicon from https://iconarchive.com/ -->
                <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
                <!-- Font Awesome icons (free version)-->
                <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
                <!-- Google fonts-->
                <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
                <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
                <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&display=swap" rel="stylesheet" type="text/css" />
                <!--        images from unsplash.com-->
                <!-- Bootstrap core JS-->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>


                <?php
                $this->ustaw_style('css/styles.css');
                $this->ustaw_style('css/slider.css');
                $this->wyswietl_slowa_kluczowe();
                ?>
            </head><body id='page-top'>
                <?php
            }

            public function wyswietl_zawartosc() {
                ?>
                <!-- Masthead-->
                <header class="masthead">
                    <div class="container">
                        <div class="masthead-subheading">Catering na każdą okazję!</div>
                        <div class="masthead-heading text-uppercase">szybko tanio smacznie</div>

                        <a class="btn btn-primary btn-xl text-uppercase" data-bs-toggle="modal" href="#servicespopup" >Poproś o kontakt</a>
                    </div>
                </header>
                <!-- Form modal popup -->
                <div class="portfolio-modal modal fade" id="servicespopup" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-lg-8">
                                        <div class="modal-body">
                                            <form action="index.php" method="post">
                                                <table class="table">
                                                    <caption class="caption-top">Podaj nam dane kontaktowe</caption>
                                                    <tbody>

                                                        <tr><td>Imię i nazwisko</td><td><input class="form-control"  type="text" placeholder="Jan Kowalski" name="imieinazwisko"></td></tr>
                                                        <tr><td>Adres</td><td><input class="form-control" type="text" placeholder="ul. Nadbystrzycka 120a" name="adres"></td></tr>
                                                        <tr><td>Telefon</td><td><input class="form-control" type="text" placeholder="123 456 789 lub 12 345 67 89" name="telefon"></td></tr>
                                                        <tr><td>Email</td><td><input class="form-control" type="email" placeholder="jan.kowalski@gmail.com" name="email"></td></tr>
                                                        <tr><td colspan="2"><input class="btn btn-primary btn-xl text-uppercase" type="submit" value="Prześlij" name="send"/></td></tr>
                                                        <tr><td colspan="2"><p class="help-block" id="display">Odezwiemy się!</p></td></tr>
                                                        <tr><td colspan="2"><input class="btn btn-danger btn-sm text-uppercase" type="reset" value="Wyczyść" /></td></tr>  

                                                    </tbody>
                                                </table>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <?php
                echo $this->zawartosc;
            }

            public function wyswietl_zawartosc_zalogowany() {
                ?>
                <!-- Masthead-->
                <header class="masthead">
                    <div class="container">
                        <div class="masthead-subheading">Catering na każdą okazję!</div>
                        <div class="masthead-heading text-uppercase">szybko tanio smacznie</div>

                        <a class="btn btn-primary btn-xl text-uppercase" data-bs-toggle="modal" href="#servicespopup" >Złóż zamówienie</a>
                    </div>
                </header>
                <!-- Form modal popup -->
                <div class="portfolio-modal modal fade" id="servicespopup" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-lg-8">
                                        <div class="modal-body">
                                            <form action="zalogowany.php" method="post">
                                                <table class="table">
                                                    <caption class="caption-top">Zamówienie</caption>
                                                    <tbody>

                                                        <tr><td>Typ:</td>
                                                            <td>
                                                                <select name="typ" class="form-control">
                                                                    <option value="Dieta indywidualna">Dieta indywidualna</option>
                                                                    <option value="Kanapki">Kanapki</option>
                                                                    <option value="Pełna oferta">Pełna oferta</option>
                                                                </select>
                                                            </td> 
                                                        </tr>
                                                        <tr>
                                                            <td>Zestaw testowy (tylko)</td>
                                                            <td><div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="zestaw[]"  value="true">Tak</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="zestaw[]"  value="false" checked>Nie</div></td>
                                                        </tr>

                                                        <tr><td>Liczba osób</td><td><input class="form-control" type="number" value="10" min="10" max="300" name="liczbaOsob"></td></tr>
                                                        <tr><td>Pakiet:</td><td>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="pakiet[]"  value="basic" checked>Obiad (1 danie)</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="pakiet[]"  value="standard">Obiad (2 dania) + deser</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="radio" name="pakiet[]"  value="premium">Obiad (2 dania) + deser + przekąski i napoje</div></td>
                                                        </tr>
                                                        <tr><td>Alergeny:</td><td>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="checkbox" name="alergeny[]"  value="nuts">Orzechy</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="checkbox" name="alergeny[]"  value="gluten">Gluten</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="checkbox" name="alergeny[]"  value="eggs">Jajka</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="checkbox" name="alergeny[]"  value="milk">Mleko</div>
                                                                <div class="form-check form-check-inline"><input class="form-check-input" type="checkbox" name="alergeny[]"  value="brak" checked>Brak</div>
                                                                <div class="form-check form-check-inline form-switch">
                                                                    <input class="form-check-input" type="checkbox" name="alergeny[]" value ="inne" >
                                                                    <label for="inneAllergeny">Inne - jakie?</label></div>
                                                                <input class="form-control" type="text" name="inneAlergeny" id="inneAllergeny" placeholder="truskawki, sezam">
                                                            </td>
                                                        </tr>
                                                        <tr><td colspan="2"><input class="btn btn-primary btn-xl text-uppercase" type="submit" value="Zamów" name="zamow"/></td></tr>
                                                        <tr><td colspan="2"><button class="btn btn-danger btn-sm text-uppercase" data-bs-dismiss="modal" type="button"><i class="fas fa-times me-1"></i>Zamknij</button></td></tr>

                                                    </tbody>
                                                </table>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <?php
                echo $this->zawartosc;
            }

            public function wyswietl_stopke() {
                ?>
                <!-- Contact-->
                <section class="page-section" id="contact">
                    <div class="container">
                        <div class="text-center">
                            <h2 class="section-heading text-uppercase">Kontakt</h2>
                        </div>
                        <div class="row align-items-stretch mb-5">
                            <div class="col-md-6">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d883.1794749788949!2d22.54868615712566!3d51.23733312863719!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4722577090bea7e7%3A0x74d3f4c5638169d5!2sNadbystrzycka%2023%2F11%2C%2020-618%20Lublin!5e0!3m2!1spl!2spl!4v1622905424696!5m2!1spl!2spl" allowfullscreen="" ></iframe>
                            </div>
                            <div class="col-md-6">
                                <h4 class="link-light">Telefon: 81 649 53 98</h4>
                                <h4 class="link-light">Telefon: 500 926 770</h4>
                                <h4 class="link-light">Email: jedzenie@czeka.pl</h4>
                                <h4 class="link-light">Adres: Nadbystrzycka 23/11 20-618 Lublin</h4>
                                <h4 class="link-light">NIP: 5361015907</h4>
                            </div>
                        </div>
                    </div>
                </section>
                <footer class="footer py-4">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-lg-4 text-lg-start">
                                Copyright &copy; Tomasz Wilkołazki
                                <script>
                                    document.write(new Date().getFullYear());
                                </script>
                            </div>
                            <div class="col-lg-4 my-3 my-lg-0">
                                <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-linkedin-in"></i></a>
                            </div>
                            <div class="col-lg-4 text-lg-end">
                                <a class="link-dark text-decoration-none me-3" href="#!">Polityka prywatności</a>
                                <a class="link-dark text-decoration-none" href="#!">Regulamin</a>
                            </div>
                        </div>
                    </div>
                </footer>
                <?php
                echo "<script src='js/scripts.js'></script>";
                echo "<script src='js/skrypty.js'></script>";
                echo "<script src='js/slider.js'></script>";
                echo '</body></html>';
            }

        }
        