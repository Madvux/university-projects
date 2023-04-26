<?php

include_once '../klasy/Baza.php';
$db = new Baza("localhost", "root", "", "projekt");

$zawartosc = "";
$zawartosc .= "<h3>Zainteresowani:</h3>";
$datarm = date('Y-m-d', strtotime('-7 days'));
$db->delete("delete from zainteresowani where data<'$datarm'");
$sql = "select *  FROM zainteresowani ORDER BY data DESC";
$pola = ["imieinazwisko", "telefon", "email", "data"];
$zawartosc .= $db->select($sql, $pola);
echo $zawartosc;
