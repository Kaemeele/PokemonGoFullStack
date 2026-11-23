import React, { createContext, useState, useEffect } from 'react';
import { login, logout, getAuthSession, isAuthenticated, isAdmin } from '../services/authService.js';
export const AuthContext = createContext();
export function AuthProvider({ children }){
  const [user, setUser] = useState(() => getAuthSession());
  useEffect(() => {
    const session = getAuthSession();
    setUser(session);
  }, []);
  const handleLogin = async (username,password) => {
    const data = await login(username,password);
    setUser({ accessToken:data.accessToken, role:data.role, username:data.username });
    return data;
  };
  const handleLogout = () => {
    logout();
    setUser(null);
  };
  return (
    <AuthContext.Provider value={{ user, isAuthenticated: isAuthenticated(), isAdmin: isAdmin(), login: handleLogin, logout: handleLogout }}>
      {children}
    </AuthContext.Provider>
  );
}
