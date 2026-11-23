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
    try {
      await login(username, password);
      navigate('/');
    } catch (err) {
      setError(getErrorMessage(err));
    }
  };
  return (
    <div className="container d-flex justify-content-center align-items-center" style={{ minHeight: '80vh' }}>
      <div className="card p-4 w-100" style={{ maxWidth: '400px' }}>
        <h2 className="card-title text-center mb-4">Login</h2>
        {error && <p className="text-danger text-center">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Usuario</label>
            <input type="text" className="form-control" value={username} onChange={e => setUsername(e.target.value)} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Contraseña</label>
            <input type="password" className="form-control" value={password} onChange={e => setPassword(e.target.value)} required />
          </div>
          <button type="submit" className="btn btn-primary w-100">Entrar</button>
        </form>
      </div>
    </div>
  );
}
export default Login;
