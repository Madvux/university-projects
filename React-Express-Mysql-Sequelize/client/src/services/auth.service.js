import axios from "axios";

const API_URL = "http://localhost:3000/api/auth/";

const register = (username, email, password, first_name, last_name, pesel, contact_number, building_number, apartment_number, street_name, city_name ,voivodeship_name, country_name, country_code) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
    first_name,
    last_name,
    pesel,
    contact_number,
    building_number,
    apartment_number,
    street_name,
    city_name,
    voivodeship_name,
    country_name,
    country_code
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL + "signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
}

export default AuthService;