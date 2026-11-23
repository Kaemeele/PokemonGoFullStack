import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../services/authService.js';
import { getErrorMessage } from '../utils/getErrorMessage.js';
function Login(){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const handleSubmit = async e => {
    e.preventDefault();
    try{await login(username,password);navigate('/');}
    catch(err){setError(getErrorMessage(err));}
  };
  return (
    <div>
      <h2>Login</h2>
      {error && <p style={{color:'red'}}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Usuario" value={username} onChange={e=>setUsername(e.target.value)} required />
        <input type="password" placeholder="Contraseña" value={password} onChange={e=>setPassword(e.target.value)} required />
        <button type="submit">Entrar</button>
      </form>
    </div>
  );
}
export default Login;
