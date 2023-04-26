<?php

class UserManager {

    function loginForm() {
        $z = '
           <caption class="caption-top">Panel logowania</caption>
<tbody>
    <tr><td>Login: </td><td><input class="form-control"  type="text" name="login" required></td></tr>
    <tr><td>Hasło: </td><td><input class="form-control" type="password" name="passwd" required></td></tr>
    <tr><td colspan="2"><input class="btn btn-primary btn-xl text-uppercase" type="submit" value="Zaloguj" name="zaloguj"/></td></tr>
    <tr><td colspan="2"><input class="btn btn-danger btn-sm text-uppercase" type="reset" value="Wyczyść" /></td></tr>
    <tr><td colspan="2">Nie masz konta? <a data-bs-toggle="modal" href="#register" class="link">Zarejestruj się</a></td></tr>
</tbody>';
        return $z;
    }

    function login($db) {
        $args = [
            'login' => FILTER_SANITIZE_ADD_SLASHES,
            'passwd' => FILTER_SANITIZE_ADD_SLASHES
        ];

        $dane = filter_input_array(INPUT_POST, $args);

        $login = $dane["login"];
        $passwd = $dane["passwd"];
        $userId = $db->selectUser($login, $passwd, "users");
        if ($userId >= 0) {
            session_start();
            $sessionId = session_id();
            $date = date("Y-m-d H:i:s");
            $sqlrm = "DELETE FROM logged_in_users WHERE userId=$userId";
            $db->delete($sqlrm);
            $sql = "INSERT INTO logged_in_users (sessionId, userId, lastUpdate) VALUES ('$sessionId','$userId','$date')";
            $db->insert($sql);
        }
        return $userId;
    }

    function logout($db) {
        session_start();
        $sessionId = session_id();
        $sqlrm = "DELETE FROM logged_in_users WHERE sessionId='$sessionId'";
        $db->delete($sqlrm);
        if (isset($_COOKIE[session_name()])) {
            setcookie(session_name(), '', time() - 42000, '/');
        }
        session_destroy();
    }

    function getLoggedInUser($db, $sessionId) {
        $id = -1;
        $sql = "SELECT * FROM logged_in_users WHERE sessionId='$sessionId'";
        if ($result = $db->getMysqli()->query($sql)) {
            $ile = $result->num_rows;
            if ($ile == 1) {
                $row = $result->fetch_object();
                $id = $row->userId;
            }
        }
        return $id;
    }

}


