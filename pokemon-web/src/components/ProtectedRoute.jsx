import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext.jsx';
export default function ProtectedRoute({ component: Component }){
  const { isAuthenticated, isAdmin } = useContext(AuthContext);
  if (!isAuthenticated) return <Navigate to="/login" replace />;
  if (!isAdmin) return <Navigate to="/" replace />;
  return <Component />;
}
