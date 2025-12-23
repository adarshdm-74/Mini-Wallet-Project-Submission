import api from './api';

export const createUser = (data) => api.post('/wallets', data);
export const getUser = (id) => api.get(`/wallets/${id}`);
export const transferMoney = (data) => api.post('/wallets/transfer', data);
