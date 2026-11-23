import React, { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import { listPublic, remove as deletePokemon } from '../services/pokemonService.js';
import { getErrorMessage } from '../utils/getErrorMessage.js';
import { AuthContext } from '../context/AuthContext.jsx';
function PokemonList(){
  const [pokemons, setPokemons] = useState([]);
  const [error, setError] = useState('');
  const { isAdmin } = useContext(AuthContext);
  const fetch = async()=>{
    try{const data=await listPublic();setPokemons(data);}
    catch(err){setError(getErrorMessage(err));}
  };
  useEffect(()=>{fetch();},[]);
  const handleDelete = async id=>{
    if(!window.confirm('¿Eliminar este Pokémon?')) return;
    try{await deletePokemon(id);await fetch();}
    catch(err){setError(getErrorMessage(err));}
  };
  return(
    <div>
      <h2>Pokémon</h2>
      {error && <p style={{color:'red'}}>{error}</p>}
      {isAdmin && <Link to="/new"><button>Nuevo Pokémon</button></Link>}
      <table>
        <thead><tr><th>ID</th><th>Nombre</th><th>Tipo</th><th>Nivel</th><th>Acciones</th></tr></thead>
        <tbody>
          {pokemons.map(p=>(
            <tr key={p.id}>
              <td>{p.id}</td>
              <td>{p.nombre}</td>
              <td>{p.tipo}</td>
              <td>{p.nivel}</td>
              <td>
                <Link to={`/edit/${p.id}`}><button>Edit</button></Link>
                {isAdmin && <button onClick={()=>handleDelete(p.id)}>Delete</button>}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
export default PokemonList;
