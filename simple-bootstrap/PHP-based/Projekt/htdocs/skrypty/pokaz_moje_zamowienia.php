<?php
include_once '../klasy/Baza.php';
include_once '../klasy/User.php';
include_once '../klasy/UserManager.php';

$db = new Baza("localhost", "root", "", "projekt");
$um = new UserManager();
session_start();
$sessionId = session_id();
$userId = $um->getLoggedInUser($db, $sessionId);

$zawartosc = "";
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
echo $zawartosc;
