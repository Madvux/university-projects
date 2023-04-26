<?php

include_once '../klasy/Baza.php';
$db = new Baza("localhost", "root", "", "projekt");
$zawartosc = "";
$zawartosc .= "<h3>Zrealizuj zamówienie nr:</h3>";
$zawartosc .= '<form action="zalogowany.php" method="POST"><input type="number" name="nrzamowienia" /><input type="submit" value="Zrealizuj" /></form> ';



$zawartosc .= "<br><h3>Wszystkie zamówienia:</h3>";
$zawartosc .= "<div class='row align-items-stretch mb-5'><div class='col-md-6'><h3>W realizacji:</h3>";
$sql = "select *  FROM zamowienia where status = 0 ORDER BY data DESC";
$pola = ["id", "typ", "liczbaOsob", "pakiet", "data"];
$zawartosc .= $db->select($sql, $pola);
$zawartosc .= "</div><div class='col-md-6'><h3>Zrealizowane:</h3>";
$sql = "select *  FROM zamowienia where status = 1 ORDER BY data DESC";
$pola = ["id", "typ", "liczbaOsob", "pakiet", "data"];
$zawartosc .= $db->select($sql, $pola);
$zawartosc .= "</div></div>";
echo $zawartosc;
