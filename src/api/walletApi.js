import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const createUser = (user) =>
  axios.post(`${BASE_URL}/users`, user);

export const transferMoney = (data) =>
  axios.post(`${BASE_URL}/transfer`, data);
