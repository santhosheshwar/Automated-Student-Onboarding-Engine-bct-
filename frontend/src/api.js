import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api/students"
});

export default API;