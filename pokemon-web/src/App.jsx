import React, { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './pages/Login.jsx';
import PokemonList from './pages/PokemonList.jsx';
import AdminBasicList from './pages/AdminBasicList.jsx';
import PokemonEditor from './pages/PokemonEditor.jsx';
import ProtectedRoute from './components/ProtectedRoute.jsx';
import { AuthContext } from './context/AuthContext.jsx';
function App(){
  const { isAuthenticated, isAdmin, logout } = useContext(AuthContext);
  return (
    <Router>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">Pokédex Gestión</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item"><Link className="nav-link" to="/">Inicio</Link></li>
              {isAdmin && <li className="nav-item"><Link className="nav-link" to="/admin">Admin</Link></li>}
              {isAuthenticated ? (
                <li className="nav-item"><button className="nav-link btn btn-link" onClick={logout}>Cerrar sesión</button></li>
              ) : (
                <li className="nav-item"><Link className="nav-link" to="/login">Iniciar sesión</Link></li>
              )}
            </ul>
          </div>
        </div>
      </nav>
      <Routes>
        <Route path="/login" element={<Login/>} />
        <Route path="/" element={<PokemonList/>} />
        <Route path="/admin" element={<ProtectedRoute component={AdminBasicList}/> } />
        <Route path="/new" element={<ProtectedRoute component={PokemonEditor}/> } />
        <Route path="/edit/:id" element={<ProtectedRoute component={PokemonEditor}/> } />
      </Routes>
    </Router>
  );
}
export default App;