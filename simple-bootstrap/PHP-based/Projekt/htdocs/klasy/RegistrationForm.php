<?php

class RegistrationForm {

    protected $user;

    function __construct() {
        $z = '
    <caption class="caption-top">Panel rejestracji</caption>
    <tbody>
    
        <tr><td>Login: </td><td><input class="form-control"  type="text" name="userName" required></td></tr>
        <tr><td>Imię i nazwisko: </td><td><input class="form-control"  type="text" name="fullName" required></td></tr>
        <tr><td>Hasło: </td><td ><input class="form-control" type="password" name="passwd" required></td></tr>
        <tr><td>Email: </td><td><input class="form-control" type="email" name="email" required></td></tr>
        <tr><td>Telefon: </td><td><input class="form-control" type="text" name="telefon" placeholder="123 456 789 lub 12 345 67 89" required></td></tr>
        <tr><td colspan="2"><input class="btn btn-primary btn-xl text-uppercase" type="submit" value="Rejestruj" name="submit"/></td></tr>
        <tr><td colspan="2"><input class="btn btn-danger btn-sm text-uppercase" type="reset" value="Wyczyść" /></td></tr>

    </tbody>';
        return $z;
    }

    function checkUser() { // podobnie jak metoda validate z lab4
        $args = [
            'userName' => ['filter' => FILTER_VALIDATE_REGEXP,
                'options' => ['regexp' => '/^[0-9A-Za-ząęłńśćźżó_-]{2,25}$/']],
            // pozostałe warunki do walidacji
            'fullName' => ['filter' => FILTER_VALIDATE_REGEXP,
                'options' => ['regexp' => '/^[A-ZĄĘŁŃŚĆŻŹÓa-ząęłńśćźżó -]{5,35}$/']],
            // pozostałe warunki do walidacji
            'passwd' => ['filter' => FILTER_VALIDATE_REGEXP,
                'options' => ['regexp' => '/^[.\s\d\D]{8,40}$/']],
            'telefon' => ['filter' => FILTER_VALIDATE_REGEXP,
                'options' => ['regexp' => '/^[\d +-]{9,15}$/']],
            // pozostałe warunki do walidacji
            'email' => ['filter' => FILTER_VALIDATE_EMAIL]];
        //przefiltruj dane:
        $dane = filter_input_array(INPUT_POST, $args);

        $errors = "";
        foreach ($dane as $key => $val) {
            if ($val === false or $val === NULL) {
                $errors .= $key . " ";
            }
        }
        if ($errors === "") {
            //Dane poprawne – utwórz obiekt user
            include_once 'klasy/User.php';
            $this->user = new User($dane['userName'], $dane['fullName'],
                    $dane['email'], $dane['passwd'], $dane['telefon']);
        } else {
            //"<p>Błędne dane:$errors</p>";
            $this->user = NULL;
        }
        return $this->user;
    }

}
