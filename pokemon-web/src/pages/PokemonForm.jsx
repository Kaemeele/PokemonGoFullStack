import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { create, update, findById } from '../services/pokemonService.js';
import { getErrorMessage } from '../utils/getErrorMessage.js';
function PokemonForm({ isEdit }){
  const navigate = useNavigate();
  const { id } = useParams();
  const [pokemon, setPokemon] = useState({ nombre:'', tipo:'', nivel:'', ps:'', ataque:'', defensa:'', velocidad:'', imagenUrl:'' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  useEffect(() => { if (isEdit) { setLoading(true); findById(id).then(data => { setPokemon({ nombre: data.nombre ?? '', tipo: data.tipo ?? '', nivel: data.nivel ?? '', ps: data.ps ?? '', ataque: data.ataque ?? '', defensa: data.defensa ?? '', velocidad: data.velocidad ?? '', imagenUrl: data.imagenUrl ?? '' }); setLoading(false); }).catch(err => { setError(getErrorMessage(err)); setLoading(false); }); } }, [isEdit, id]);
  const handleChange=e=>{const {name,value}=e.target;setPokemon(prev=>({ ...prev, [name]: value }));};
  const handleSubmit=async e=>{e.preventDefault();setLoading(true);try{if(isEdit){await update(id,pokemon);}else{await create(pokemon);}navigate('/');}catch(err){setError(getErrorMessage(err));}finally{setLoading(false);}};
  return(
    <div>
      <h2>{isEdit?'Editar':'Crear'} Pokémon</h2>
      {error && <p style={{color:'red'}}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <input name="nombre" placeholder="Nombre" value={pokemon.nombre} onChange={handleChange} required />
        <input name="tipo" placeholder="Tipo" value={pokemon.tipo} onChange={handleChange} />
        <input name="nivel" placeholder="Nivel" type="number" value={pokemon.nivel} onChange={handleChange} />
        <input name="ps" placeholder="PS" type="number" value={pokemon.ps} onChange={handleChange} />
        <input name="ataque" placeholder="Ataque" type="number" value={pokemon.ataque} onChange={handleChange} />
        <input name="defensa" placeholder="Defensa" type="number" value={pokemon.defensa} onChange={handleChange} />
        <input name="velocidad" placeholder="Velocidad" type="number" value={pokemon.velocidad} onChange={handleChange} />
        <input name="imagenUrl" placeholder="Imagen URL" value={pokemon.imagenUrl} onChange={handleChange} />
        <button type="submit" disabled={loading}>{loading?'Guardando...':'Guardar'}</button>
      </form>
    </div>
  );
}
export default PokemonForm;
