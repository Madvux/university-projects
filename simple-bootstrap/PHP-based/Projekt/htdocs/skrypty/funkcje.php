<?php

include_once "klasy/Baza.php";

function walidacja_zamowienia($db, $userId) {
    $args = ['typ' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
        'zestaw' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'flags' => FILTER_REQUIRE_ARRAY],
        'liczbaOsob' => FILTER_VALIDATE_INT,
        'pakiet' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'flags' => FILTER_REQUIRE_ARRAY],
        'alergeny' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'flags' => FILTER_REQUIRE_ARRAY],
        'inneAlergeny' => ['filter' => FILTER_VALIDATE_REGEXP,
            'options' => ['regexp' => '/^[A-ZĄĘŁŃŚĆŹŻÓa-ząęłńśćźżó-]{0,30}$/']]];
    $dane = filter_input_array(INPUT_POST, $args);
    $errors = "";
    foreach ($dane as $key => $val) {
        if ($val === false or $val === NULL) {
            $errors .= $key . " ";
        }
    }


    if ($errors === "") {

        $typ = $dane['typ'];
        $liczbaOsob = $dane['liczbaOsob'];
        $zestaw = implode(",", $dane['zestaw']);
        $pakiet = implode(",", $dane['pakiet']);
        $alergeny = implode(",", $dane['alergeny']);
        $inneAlergeny = $dane['inneAlergeny'];

        $sql = "INSERT INTO zamowienia (userId, typ, liczbaOsob, zestaw, pakiet, alergeny, inneAlergeny) VALUES ('$userId', '$typ', '$liczbaOsob', '$zestaw', '$pakiet', '$alergeny','$inneAlergeny')";

        $db->insert($sql);
    } else {
        echo "<script>alert('Nie poprawne dane: $errors')</script>";
    }
}

function dodaj_zamowienie($db, $userId) {
    echo "<script>alert('Zamówienie zostało przyjęte do realizacji!')</script>";
    walidacja_zamowienia($db, $userId);
}

function walidacja_kontaktu($db) {
    $args = ['imieinazwisko' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'filter' => FILTER_SANITIZE_ADD_SLASHES],
        'adres' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'filter' => FILTER_SANITIZE_ADD_SLASHES],
        'telefon' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'filter' => FILTER_SANITIZE_ADD_SLASHES],
        'email' => ['filter' => FILTER_SANITIZE_FULL_SPECIAL_CHARS,
            'filter' => FILTER_SANITIZE_ADD_SLASHES],];

    $dane = filter_input_array(INPUT_POST, $args);
    $errors = "";
    foreach ($dane as $key => $val) {
        if ($val === false or $val === NULL or $val === "") {
            $errors .= $key . " ";
        }
    }
    if ($errors === "") {

        $imieinazwisko = $dane['imieinazwisko'];
        $adres = $dane['adres'];
        $telefon = $dane['telefon'];
        $email = $dane['email'];
        $data = date("Y-m-d H:i:s");
        $sql = "SELECT * FROM zainteresowani";
        $sqlrmtel = "DELETE FROM zainteresowani WHERE telefon=$telefon";
        $sqlrmemail = "DELETE FROM zainteresowani WHERE email=$email";
        $db->delete($sqlrmtel);
        $db->delete($sqlrmemail);
        $sql = "INSERT INTO zainteresowani (imieinazwisko, adres, telefon, email, data) VALUES ('$imieinazwisko', '$adres', '$telefon', '$email', '$data')";
        $db->insert($sql);
    } else {
        echo "<script>alert('Błąd: $errors')</script>";
    }
}

function dodaj_kontakt($db) {
    echo "<script>alert('Odezwiemy się gdy tylko znajdziemy chwilę!')</script>";
    walidacja_kontaktu($db);
}
