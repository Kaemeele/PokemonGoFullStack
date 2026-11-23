import axios from 'axios';
const baseURL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';
const httpClient = axios.create({
  baseURL,
  timeout: 10000
});
httpClient.interceptors.request.use(config => {
  if (!config.url.includes('/auth/login')) {
    const token = sessionStorage.getItem('accessToken');
    if (token) config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});
httpClient.interceptors.response.use(response => response, error => {
  if (error.response && error.response.status === 401) {
    sessionStorage.clear();
    window.location.href = '/login';
  }
  return Promise.reject(error);
});
export default httpClient;
