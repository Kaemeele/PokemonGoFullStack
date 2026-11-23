import React, { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import { listPublic, remove as deletePokemon } from '../services/pokemonService.js';
import { getErrorMessage } from '../utils/getErrorMessage.js';
import { AuthContext } from '../context/AuthContext.jsx';
function PokemonList() {
  const [pokemons, setPokemons] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);
  const [deletingId, setDeletingId] = useState(null);
  const { isAuthenticated, isAdmin, logout } = useContext(AuthContext);
  const fetch = async () => {
    try {
      const data = await listPublic();
      setPokemons(data);
    } catch (err) {
      setError(getErrorMessage(err));
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => { fetch(); }, []);
  const handleDelete = async id => {
    if (!window.confirm('¿Eliminar este Pokémon?')) return;
    setDeletingId(id);
    try {
      await deletePokemon(id);
      await fetch();
    } catch (err) {
      setError(getErrorMessage(err));
    } finally {
      setDeletingId(null);
    }
  };
  if (loading) return <p>Cargando...</p>;
  return (
    <div>
      <h2>Pokémon</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {isAuthenticated ? (
        <>
          <button onClick={logout}>Cerrar sesión</button>
          {isAdmin && <Link to="/admin"><button>Admin</button></Link>}
        </>
      ) : (
        <Link to="/login"><button>Login</button></Link>
      )}
      {isAdmin && <Link to="/new"><button>Nuevo Pokémon</button></Link>}
      <table>
        <thead>
          <tr>
            <th>ID</th><th>Nombre</th><th>Tipo</th><th>Nivel</th>
            <th>PS</th><th>Ataque</th><th>Defensa</th><th>Velocidad</th>
            {isAdmin && <th>Acciones</th>}
          </tr>
        </thead>
        <tbody>
          {pokemons.map(p => (
            <tr key={p.id}>
              <td>{p.id}</td><td>{p.nombre}</td><td>{p.tipo}</td><td>{p.nivel}</td>
              <td>{p.ps}</td><td>{p.ataque}</td><td>{p.defensa}</td><td>{p.velocidad}</td>
              {isAdmin && (
                <td>
                  <Link to={`/edit/${p.id}`}><button>Edit</button></Link>
                  <button onClick={() => handleDelete(p.id)} disabled={deletingId===p.id}>
                    {deletingId===p.id ? 'Eliminando...' : 'Delete'}
                  </button>
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
      {pokemons.length === 0 && <p>No hay Pokémon registrados.</p>}
    </div>
  );
}
export default PokemonList;
