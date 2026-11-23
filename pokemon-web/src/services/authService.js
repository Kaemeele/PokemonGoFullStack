import httpClient from '../api/httpClient.js';
export async function login(username,password){
  const response=await httpClient.post('/auth/login',{username,password});
  const data=response.data;
  sessionStorage.setItem('accessToken',data.accessToken);
  sessionStorage.setItem('tokenType',data.tokenType);
  sessionStorage.setItem('expiresIn',data.expiresIn);
  sessionStorage.setItem('role',data.role);
  sessionStorage.setItem('username',data.username);
  return data;
}
export function logout(){
  sessionStorage.clear();
  window.location.href='/login';
}
export function getAuthSession(){
  return {
    accessToken:sessionStorage.getItem('accessToken'),
    role:sessionStorage.getItem('role'),
    username:sessionStorage.getItem('username')
  };
}
export function isAuthenticated(){
  return Boolean(sessionStorage.getItem('accessToken'));
}
export function isAdmin(){
  return sessionStorage.getItem('role')==='ADMIN';
}
