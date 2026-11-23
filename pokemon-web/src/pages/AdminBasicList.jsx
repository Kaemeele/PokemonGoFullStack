import React, { useEffect, useState } from 'react';
import { listBasicAdmin } from '../services/pokemonService.js';
import { getErrorMessage } from '../utils/getErrorMessage.js';
function AdminBasicList(){
  const [pokemons, setPokemons] = useState([]);
  const [error, setError] = useState('');
  const fetch = async()=>{
    try{const data=await listBasicAdmin();setPokemons(data);}
    catch(err){setError(getErrorMessage(err));}
  };
  useEffect(()=>{fetch();},[]);
  return(
    <div>
      <h2>Listado básico (admin)</h2>
      {error && <p style={{color:'red'}}>{error}</p>}
      <table>
        <thead><tr><th>ID</th><th>Nombre</th></tr></thead>
        <tbody>
          {pokemons.map(p=>(
            <tr key={p.id}><td>{p.id}</td><td>{p.nombre}</td></tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
export default AdminBasicList;
