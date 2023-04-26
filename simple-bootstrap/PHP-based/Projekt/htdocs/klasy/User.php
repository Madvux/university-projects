<?php

class User {

    const STATUS_USER = 1;
    const STATUS_ADMIN = 2;

    protected $userName;
    protected $passwd;
    protected $fullName;
    protected $email;
    protected $telefon;
    protected $date;
    protected $status;

    function __construct($userName, $fullName, $email, $passwd, $telefon) {

        $this->status = User::STATUS_USER;
        $this->passwd = password_hash($passwd, PASSWORD_DEFAULT);
        $this->fullName = $fullName;
        $this->userName = $userName;
        $this->telefon = $telefon;
        $this->email = $email;
        $this->date = date("Y-m-d");
    }

    function toArray() {
        $arr = [
            "userName" => $this->userName,
            "fullName" => $this->fullName,
            "email" => $this->email,
            "passwd" => $this->passwd,
            "date" => $this->date,
            "status" => $this->status,
            "telefon" => $this->telefon,
        ];
        return $arr;
    }

    public function getStatus() {
        return $this->status;
    }

    public function setStatus($status): void {
        $this->status = $status;
    }

    public function show() {
        echo "$this->userName  $this->passwd $this->fullName $this->email $this->status $this->date<br>";
    }

    public function getUserName() {
        return $this->userName;
    }

    public function getPasswd() {
        return $this->passwd;
    }

    public function getFullName() {
        return $this->fullName;
    }

    public function getEmail() {
        return $this->email;
    }

    public function getDate() {
        return $this->date;
    }

    public function setUserName($userName): void {
        $this->userName = $userName;
    }

    public function setPasswd($passwd): void {
        $this->passwd = $passwd;
    }

    public function setFullName($fullName): void {
        $this->fullName = $fullName;
    }

    public function setEmail($email): void {
        $this->email = $email;
    }

    public function setDate($date): void {
        $this->date = $date;
    }

    public function saveDB($db) {
        $sql = "INSERT INTO users (userName, fullName, email, passwd, status, date, telefon) VALUES"
                . "('$this->userName',"
                . "'$this->fullName',"
                . "'$this->email',"
                . "'$this->passwd',"
                . "'$this->status',"
                . "'$this->date',"
                . "'$this->telefon')";
        $db->insert($sql);
    }

    static public function getAllUsersFromDB($db) {
        $sql = "SELECT * FROM users";
        $pola = ["id", "userName", "fullName", "email", "passwd", "status", "date", "telefon"];
        echo $db->select($sql, $pola);
    }

}
