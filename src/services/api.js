import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:64074/api', // backend port
});

export default api;
