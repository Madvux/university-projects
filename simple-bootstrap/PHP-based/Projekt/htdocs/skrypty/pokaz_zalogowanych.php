<?php
include_once '../klasy/Baza.php';
$db = new Baza("localhost", "root", "", "projekt");
$zawartosc = "";

$zawartosc .= "<h3>Obecnie zalogowani: </h3>";
$sql = "select username, lastUpdate FROM logged_in_users, users where userId=Id";
$pola = ["username", "lastUpdate"];
$zawartosc .= $db->select($sql, $pola);
echo $zawartosc;
